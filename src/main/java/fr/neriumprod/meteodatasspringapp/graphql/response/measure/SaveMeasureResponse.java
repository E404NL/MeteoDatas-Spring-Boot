package fr.neriumprod.meteodatasspringapp.graphql.response.measure;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveMeasureResponse {
    private boolean success;
    private String message;
    private Measure measure;
}
