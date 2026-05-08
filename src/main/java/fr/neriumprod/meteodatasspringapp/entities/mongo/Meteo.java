package fr.neriumprod.meteodatasspringapp.entities.mongo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Meteo {
    private Float temperature;  // air temperature

    private Float humidityRate; // humidity rate

    private Long airPressure;   // air pressure

    @Override
    public String toString() {
        return "Meteo{" +
                "temperature=" + temperature +
                ", humidityRate=" + humidityRate +
                ", airPressure=" + airPressure +
                '}';
    }
}
