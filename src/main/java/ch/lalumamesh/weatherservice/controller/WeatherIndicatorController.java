package ch.lalumamesh.weatherservice.controller;

import ch.lalumamesh.weatherservice.model.dto.WeatherIndicatorResponse;
import ch.lalumamesh.weatherservice.service.WeatherIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/weather/indicator/")
public class WeatherIndicatorController {
    private final WeatherIndicatorService weatherIndicatorService;

    @Autowired
    public WeatherIndicatorController(WeatherIndicatorService weatherIndicatorService) {
        this.weatherIndicatorService = weatherIndicatorService;
    }

    @GetMapping
    public int get() {
        return this.weatherIndicatorService.getSevereWeatherIndicator().getScale();
    }
}
