package ch.lalumamesh.weatherservice.controller;

import ch.lalumamesh.weatherservice.model.db.AirPressure;
import ch.lalumamesh.weatherservice.service.AirPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather/air_pressure/")
public class AirPressureController {
    private final AirPressureService airPressureService;

    @Autowired
    public AirPressureController(AirPressureService airPressureService) {
        this.airPressureService = airPressureService;
    }

    @PostMapping
    public AirPressure add(@RequestBody AirPressure airPressure) {
        return this.airPressureService.add(airPressure);
    }

    @GetMapping
    public List<AirPressure> getAll() {
        return this.airPressureService.getAll();
    }
    @GetMapping
    @RequestMapping("latest")
    public double getLatest() {
        return this.airPressureService.getLatest().getPressure();
    }
}
