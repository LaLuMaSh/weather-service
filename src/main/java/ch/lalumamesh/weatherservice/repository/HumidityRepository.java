package ch.lalumamesh.weatherservice.repository;

import ch.lalumamesh.weatherservice.model.db.Humidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {
    List<Humidity> findTop1ByOrderByTimeDesc();
}
