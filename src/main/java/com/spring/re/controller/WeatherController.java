package com.spring.re.controller;

import com.spring.re.model.WeatherData;
import com.spring.re.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/pincode")
    public ResponseEntity<WeatherData> getWeatherForPincode(@RequestParam String pincode, @RequestParam String for_date) {
        LocalDate date = LocalDate.parse(for_date);
        WeatherData weatherData = weatherService.getWeatherByPincodeAndDate(pincode, date);
        return ResponseEntity.ok(weatherData);
    }
}
