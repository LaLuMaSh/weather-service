package ch.lalumamesh.weatherservice.controller;

import ch.lalumamesh.weatherservice.model.db.Humidity;
import ch.lalumamesh.weatherservice.service.HumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather/humidity/")
public class HumidityController {
    private final HumidityService humidityService;

    @Autowired
    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @PostMapping
    public Humidity add(@RequestBody Humidity humidity) {
        return this.humidityService.add(humidity);
    }

    @GetMapping
    public List<Humidity> getAll() {
        return this.humidityService.getAll();
    }

    @GetMapping
    @RequestMapping("latest")
    public short getLatest() {
        return this.humidityService.getLatest().getHumidity();
    }
}
