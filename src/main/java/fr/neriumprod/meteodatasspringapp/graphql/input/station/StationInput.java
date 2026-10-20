package fr.neriumprod.meteodatasspringapp.graphql.input.station;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import fr.neriumprod.meteodatasspringapp.graphql.input.Battery.BatteryInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationInput {
    private Long userId;
    private LocalDateTime startServiceDate;
    private LocalDateTime lastMeasureDate;
    private Long totalMeasures;
    private String firmwareVersion;
    private BatteryInput batteryInput;

    public StationInput(Station station){
        this.userId = station.getUserId();
        this.startServiceDate = station.getStartServiceDate();
        this.lastMeasureDate = station.getLastMeasureDate();
        this.totalMeasures = station.getTotalMeasures();
        this.firmwareVersion = station.getFirmwareVersion();
        this.batteryInput = new BatteryInput(station.getBattery().getBatteryState(),
                station.getBattery().getOutputVoltage());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartServiceDate() {
        return startServiceDate;
    }

    public void setStartServiceDate(LocalDateTime startServiceDate) {
        this.startServiceDate = startServiceDate;
    }

    public LocalDateTime getLastMeasureDate() {
        return lastMeasureDate;
    }

    public void setLastMeasureDate(LocalDateTime lastMeasureDate) {
        this.lastMeasureDate = lastMeasureDate;
    }

    public Long getTotalMeasures() {
        return totalMeasures;
    }

    public void setTotalMeasures(Long totalMeasures) {
        this.totalMeasures = totalMeasures;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public BatteryInput getBatteryInput() {
        return batteryInput;
    }

    public void setBatteryInput(BatteryInput batteryInput) {
        this.batteryInput = batteryInput;
    }
}
