package fr.neriumprod.meteodatasspringapp.graphql.input.meteo;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MeteoInput {
    private Float temperature;
    private Float humidityRate;
    private Long airPressure;

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidityRate() {
        return humidityRate;
    }

    public void setHumidityRate(Float humidityRate) {
        this.humidityRate = humidityRate;
    }

    public Long getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Long airPressure) {
        this.airPressure = airPressure;
    }
}
