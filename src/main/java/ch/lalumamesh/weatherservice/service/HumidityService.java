package ch.lalumamesh.weatherservice.service;

import ch.lalumamesh.weatherservice.model.db.Humidity;
import ch.lalumamesh.weatherservice.repository.HumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HumidityService {
    private final HumidityRepository humidityRepository;

    @Autowired
    public HumidityService(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }

    public List<Humidity> getAll() {
        return this.humidityRepository.findAll();
    }


    public Humidity getLatest() {
        return this.humidityRepository.findTop1ByOrderByTimeDesc().stream().findAny().orElse(null);
    }


    public Humidity add(Humidity humidity) {
        if (humidity.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cant override humidity with id " + humidity.getId());
        }

        if (humidity.getTime() == null) {
            humidity.setTime(LocalDateTime.now());
        }

        return this.humidityRepository.save(humidity);
    }
}
