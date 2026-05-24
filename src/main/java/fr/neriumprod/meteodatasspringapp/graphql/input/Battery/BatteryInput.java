package fr.neriumprod.meteodatasspringapp.graphql.input.Battery;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BatteryInput {
    private Integer batteryState;
    private Float outputVoltage;

    public Integer getBatteryState() {
        return batteryState;
    }

    public void setBatteryState(Integer batteryState) {
        this.batteryState = batteryState;
    }

    public Float getOutputVoltage() {
        return outputVoltage;
    }

    public void setOutputVoltage(Float outputVoltage) {
        this.outputVoltage = outputVoltage;
    }
}
