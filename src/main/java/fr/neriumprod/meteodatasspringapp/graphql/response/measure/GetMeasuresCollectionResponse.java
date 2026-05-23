package fr.neriumprod.meteodatasspringapp.graphql.response.measure;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class GetMeasuresCollectionResponse {
    private boolean success;
    private String message;
    private Collection<Measure> measures;

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

    public Collection<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(Collection<Measure> measures) {
        this.measures = measures;
    }
}
