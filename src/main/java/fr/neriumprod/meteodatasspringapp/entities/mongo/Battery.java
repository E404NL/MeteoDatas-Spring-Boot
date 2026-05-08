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
public class Battery {
    private Integer batteryState;   // percentage of energy available on the capacity

    private Float outputVoltage;    // output voltage from the battery

    @Override
    public String toString() {
        return "Battery{" +
                "batteryState=" + batteryState +
                ", outputVoltage=" + outputVoltage +
                '}';
    }
}
