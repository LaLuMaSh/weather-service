package ch.lalumamesh.weatherservice.model.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WeatherInformation {
    double temperature;
    double airPressure;
    short humidity;
    LocalDateTime time;
}
