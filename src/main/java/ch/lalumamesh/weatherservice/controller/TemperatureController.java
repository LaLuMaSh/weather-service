package ch.lalumamesh.weatherservice.controller;

import ch.lalumamesh.weatherservice.model.db.Temperature;
import ch.lalumamesh.weatherservice.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather/temperature/")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping
    public Temperature add(@RequestBody Temperature temperature) {
        return this.temperatureService.add(temperature);
    }

    @GetMapping
    public List<Temperature> getAll() {
        return this.temperatureService.getAll();
    }

    @GetMapping
    @RequestMapping("latest")
    public double getLatest() {
        return this.temperatureService.getLatest().getTemperature();
    }
}
