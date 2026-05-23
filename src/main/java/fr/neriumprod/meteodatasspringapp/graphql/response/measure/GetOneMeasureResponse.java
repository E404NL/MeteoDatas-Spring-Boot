package fr.neriumprod.meteodatasspringapp.graphql.response.measure;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOneMeasureResponse {
    private boolean success;
    private String message;
    private Measure measure;

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

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
