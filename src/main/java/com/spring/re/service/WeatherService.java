package com.spring.re.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.time.LocalDate;
import org.springframework.web.client.RestTemplate;
import com.spring.re.model.WeatherData;
import com.spring.re.repository.WeatherDataRepository;

@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String GEOCODING_API_KEY = "717c8e6ed8854a0105f45b8144291dd1";
    private final String WEATHER_API_KEY = "717c8e6ed8854a0105f45b8144291dd1";

    public WeatherData getWeatherByPincodeAndDate(String pincode, LocalDate date) {
        Optional<WeatherData> weatherDataOptional = weatherDataRepository.findByPincodeAndDate(pincode, date);

        if (weatherDataOptional.isPresent()) {
            return weatherDataOptional.get();
        }

        double[] latLong = getLatLongFromPincode(pincode);
        WeatherData weatherData = fetchWeatherFromOpenWeather(latLong[0], latLong[1], date);

        weatherData.setPincode(pincode);
        weatherData.setLatitude(latLong[0]);
        weatherData.setLongitude(latLong[1]);
        weatherData.setDate(date);
        weatherDataRepository.save(weatherData);

        return weatherData;
    }

    private double[] getLatLongFromPincode(String pincode) {
        String geocodingApiUrl = "http://api.openweathermap.org/geo/1.0/zip?zip=" + pincode + ",IN&appid=" + GEOCODING_API_KEY;
        // Assume RestTemplate makes the call and returns a Map
        Map<String, Object> response = restTemplate.getForObject(geocodingApiUrl, Map.class);
        double latitude = (double) response.get("lat");
        double longitude = (double) response.get("lon");
        return new double[] { latitude, longitude };
    }

    private WeatherData fetchWeatherFromOpenWeather(double lat, double lon, LocalDate date) {
        String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + WEATHER_API_KEY;

        Map<String, Object> weatherResponse = restTemplate.getForObject(weatherApiUrl, Map.class);

        // Extract weather data safely
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) weatherResponse.get("weather");
        String description = (String) weatherList.get(0).get("description");

        Map<String, Object> mainData = (Map<String, Object>) weatherResponse.get("main");

        // Handle temperature safely
        Number tempNumber = (Number) mainData.get("temp");
        double temperature = tempNumber.doubleValue();  // This will work whether it's Integer or Double

        // Handle humidity safely
        Number humidityNumber = (Number) mainData.get("humidity");
        double humidity = humidityNumber.doubleValue();  // This will work whether it's Integer or Double

        // Create and return WeatherData object
        WeatherData weatherData = new WeatherData();
        weatherData.setWeatherDescription(description);
        weatherData.setTemperature(temperature);
        weatherData.setHumidity(humidity);

        return weatherData;
    }

}
