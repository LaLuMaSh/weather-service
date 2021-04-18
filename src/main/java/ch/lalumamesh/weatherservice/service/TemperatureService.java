package ch.lalumamesh.weatherservice.service;

import ch.lalumamesh.weatherservice.model.db.Temperature;
import ch.lalumamesh.weatherservice.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public List<Temperature> getAll() {
        return this.temperatureRepository.findAll();
    }


    public Temperature getLatest() {
        return this.temperatureRepository.findTop1ByOrderByTimeDesc().stream().findAny().orElse(null);
    }

    public Temperature add(Temperature temperature) {
        if (temperature.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cant override temperature with id " + temperature.getId());
        }

        if (temperature.getTime() == null) {
            temperature.setTime(LocalDateTime.now());
        }


        return this.temperatureRepository.save(temperature);
    }
}
