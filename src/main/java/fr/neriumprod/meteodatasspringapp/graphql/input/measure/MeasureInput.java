package fr.neriumprod.meteodatasspringapp.graphql.input.measure;

import fr.neriumprod.meteodatasspringapp.graphql.input.Battery.BatteryInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.meteo.MeteoInput;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class MeasureInput {
    private String thingId;
    private MeteoInput meteo;
    private BatteryInput battery;

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public MeteoInput getMeteoInput() {
        return meteo;
    }

    public void setMeteoInput(MeteoInput meteoInput) {
        this.meteo = meteoInput;
    }

    public BatteryInput getBatteryInput() {
        return battery;
    }

    public void setBatteryInput(BatteryInput batteryInput) {
        this.battery = batteryInput;
    }
}
