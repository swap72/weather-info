# Weather Info API for Pincode and Date

This project is a **Spring Boot REST API** that fetches and returns weather information for a given pincode and date using **OpenWeather API**. The application optimizes API calls by storing weather data in an H2 database (volatile DB) and reuses it on subsequent requests.

## Table of Contents

1. [Features](#features)
2. [Requirements](#requirements)
3. [Project Setup](#project-setup)
4. [API Endpoints](#api-endpoints)
5. [Database](#database)
6. [Testing](#testing)
7. [Technologies Used](#technologies-used)
8. [Visual references](#visual-references)


---

## Features

- Fetch weather information based on a **pincode** and **date**.
- Use **OpenWeather Geocoding API** to convert pincode to latitude/longitude.
- Fetch weather data using **OpenWeather Current Weather API**.
- Store fetched weather data in an RDBMS (H2 or MySQL).
- Optimize future requests by retrieving data from the database if it's already stored.
- Save separate data for pincode, latitude, longitude, and weather information.
- Can also access the data using SQL commands through H2DB console. (while the SpringBoot Application is running)

---

## Requirements

To run this project, you will need the following:

- Java 8+ (or compatible JDK)
- Maven (or Gradle)
- Spring Boot
- OpenWeather API key (sign up at [OpenWeather](https://home.openweathermap.org/users/sign_up))
- Optional: MySQL (if using instead of H2)

---

## Project Setup

Follow these steps to get the project up and running:

1. **To Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/weather-info-api.git
   cd weather-info-api
   ```

2. **If Configure own API Keys**:
   Open `WeatherService.java` and replace `"your_openweather_api_key"` with your actual OpenWeather API key:
   ```java
   private final String GEOCODING_API_KEY = "your_openweather_api_key";
   private final String WEATHER_API_KEY = "your_openweather_api_key";
   ```

3. **Run the Application**:
   - If using **H2 Database** (default):
     ```bash
     mvn spring-boot:run
     ```
   - If using **MySQL**, configure the connection in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     ```

4. **Access the H2 Console**:
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - username: sa
   - password:

5. **To Test The Project(API)**
   - Simply download the project as .zip
   - Extract the project folder
   - Can work on intelliJ IDEA, Eclipse but STS is recommended
   - Import the project as Maven Project click finish
   - Right click project start as a Spring Boot Application
   - Test the API on PostMan (Refer Example request below)
  
   
---

## API Endpoints

### 1. **Get Weather Information**

- **URL**: `/api/weather/pincode`
- **Method**: `GET`
- **Description**: Fetch weather information for a given pincode and date.
  
  **Query Parameters**:
  - `pincode`: Postal code (e.g., 560083)
  - `for_date`: Date in `YYYY-MM-DD` format
  
  **Example Request**:
  ```bash
  GET http://localhost:8080/api/weather/pincode?pincode=560083&for_date=2024-10-21
  ```

  **Response**:
  ```json
  {
    "id": 4,
    "pincode": "560083",
    "latitude": 12.8608,
    "longitude": 77.5896,
    "date": "2024-10-19",
    "weatherDescription": "light rain",
    "temperature": 295.61,
    "humidity": 89.0
  }
  ```

---

## Database

- **H2 Database**: The project uses H2 by default for development and testing. You can check the database using the **H2 Console**.
  
  - **H2 Console URL**: `http://localhost:8080/h2-console`
  - **JDBC URL**: `jdbc:h2:mem:testdb`
  

---

## Testing

You can test the API using **Postman**.

### Example Test via Postman:

1. **Method**: `GET`
2. **URL**: `http://localhost:8080/api/weather/pincode`
3. **Query Parameters**:
   - `pincode`: 560083
   - `for_date`: 2024-10-21
4. **Expected Response**:
   ```json
   {
    "id": 4,
    "pincode": "560083",
    "latitude": 12.8608,
    "longitude": 77.5896,
    "date": "2024-10-19",
    "weatherDescription": "light rain",
    "temperature": 295.61,
    "humidity": 89.0
   }
   ```

---

## Technologies Used

- **Spring Boot**: To create RESTful APIs.
- **OpenWeather API**: To fetch weather and geolocation data.
- **H2 Database**: Default in-memory database for testing.
- **Maven**: For project management and building.
- **Spring Tool Suite**: As IDE.
---

## Visual references
![ss1](https://github.com/user-attachments/assets/39b8dd9d-524c-4622-bb54-704616f740a1)
![ss2](https://github.com/user-attachments/assets/fe4ffcc7-3d71-4f0a-973f-b15d50ad8f6b)
![ss3](https://github.com/user-attachments/assets/1ea728f6-f42b-48ae-b7f2-10ec8567399c)

