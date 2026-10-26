package fr.neriumprod.meteodatasspringapp.graphql.response.station;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveStationResponse {
    private boolean success;
    private String message;
    private Station station;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
