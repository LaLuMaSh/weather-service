package ch.lalumamesh.weatherservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherIndicatorResponse {
    String message;
    int scale;
}
