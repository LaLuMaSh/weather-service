package ch.lalumamesh.weatherservice.repository;

import ch.lalumamesh.weatherservice.model.db.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    List<Temperature> findTop1ByOrderByTimeDesc();
}
