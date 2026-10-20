package fr.neriumprod.meteodatasspringapp.graphql.response.station;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class GetStationCollectionResponse {
    private boolean success;
    private String message;
    private Collection<Station> stations;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public Collection<Station> getStations() {
        return stations;
    }

    public void setStations(Collection<Station> stations) {
        this.stations = stations;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
