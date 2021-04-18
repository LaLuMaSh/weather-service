package ch.lalumamesh.weatherservice.repository;

import ch.lalumamesh.weatherservice.model.db.AirPressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirPressureRepository extends JpaRepository<AirPressure, Long> {
    List<AirPressure> findTop1ByOrderByTimeDesc();
}
