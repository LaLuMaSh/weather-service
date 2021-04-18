package ch.lalumamesh.weatherservice.service;

import ch.lalumamesh.weatherservice.model.db.AirPressure;
import ch.lalumamesh.weatherservice.model.db.Humidity;
import ch.lalumamesh.weatherservice.model.db.Temperature;
import ch.lalumamesh.weatherservice.model.dto.WeatherIndicatorResponse;
import ch.lalumamesh.weatherservice.model.internal.WeatherInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class WeatherIndicatorService {
    private final AirPressureService airPressureService;
    private final HumidityService humidityService;
    private final TemperatureService temperatureService;

    @Autowired
    public WeatherIndicatorService(AirPressureService airPressureService, HumidityService humidityService, TemperatureService temperatureService) {
        this.airPressureService = airPressureService;
        this.humidityService = humidityService;
        this.temperatureService = temperatureService;
    }


    public WeatherIndicatorResponse getSevereWeatherIndicator() {
        WeatherInformation weatherInformation = getWeatherInformation();

        int i = new Random().nextInt(5) + 1;
        return new WeatherIndicatorResponse(
                "Temperature: " + weatherInformation.getTemperature() + "; " +
                "Humidity: " + weatherInformation.getHumidity() + "; " +
                "Air Pressure: " + weatherInformation.getAirPressure() + "; " +
                "There is no way that i found that allows me to accurately predict the weather from just these values, the returned value is a random number."
                , i);
    }


    private WeatherInformation getWeatherInformation() {
        LocalDateTime min;
        LocalDateTime max;

        AirPressure airPressureLatest = this.airPressureService.getLatest();

        if (airPressureLatest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no latest entry for air pressure found.");
        }
        min = airPressureLatest.getTime();
        max = airPressureLatest.getTime();

        Temperature temperatureLatest = this.temperatureService.getLatest();

        if (temperatureLatest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no latest entry for temperature found.");
        }

        if (min.isAfter(temperatureLatest.getTime())) {
            min = temperatureLatest.getTime();
        }
        if (max.isBefore(temperatureLatest.getTime())) {
            max = temperatureLatest.getTime();
        }

        Humidity humidityLatest = this.humidityService.getLatest();

        if (humidityLatest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no latest entry for humidity found.");
        }

        if (min.isAfter(humidityLatest.getTime())) {
            min = humidityLatest.getTime();
        }
        if (max.isBefore(humidityLatest.getTime())) {
            max = humidityLatest.getTime();
        }

        Duration between = Duration.between(min, max);

        if (!between.minusHours(1).isNegative()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "time difference between two dates is bigger then 2 hours: " + min + ", " + max);
        }

        return new WeatherInformation(
                temperatureLatest.getTemperature(),
                airPressureLatest.getPressure(),
                humidityLatest.getHumidity(),
                min.plusSeconds(ChronoUnit.SECONDS.between(min, max) / 2)
        );
    }
}
