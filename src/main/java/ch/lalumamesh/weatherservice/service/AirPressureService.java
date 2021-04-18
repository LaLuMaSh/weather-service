package ch.lalumamesh.weatherservice.service;

import ch.lalumamesh.weatherservice.model.db.AirPressure;
import ch.lalumamesh.weatherservice.repository.AirPressureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirPressureService {
    private final AirPressureRepository airPressureRepository;

    @Autowired
    public AirPressureService(AirPressureRepository airPressureRepository) {
        this.airPressureRepository = airPressureRepository;
    }

    public List<AirPressure> getAll() {
        return this.airPressureRepository.findAll();
    }

    public AirPressure getLatest() {
        return this.airPressureRepository.findTop1ByOrderByTimeDesc().stream().findAny().orElse(null);
    }

    public AirPressure add(AirPressure airPressure) {
        if (airPressure.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cant override air pressure with id " + airPressure.getId());
        }

        if (airPressure.getTime() == null) {
            airPressure.setTime(LocalDateTime.now());
        }

        return this.airPressureRepository.save(airPressure);
    }
}
