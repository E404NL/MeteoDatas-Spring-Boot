package fr.neriumprod.meteodatasspringapp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class MeasureTemperatureDTO {
    private LocalDateTime timestamp;
    private Float temperature;
}
