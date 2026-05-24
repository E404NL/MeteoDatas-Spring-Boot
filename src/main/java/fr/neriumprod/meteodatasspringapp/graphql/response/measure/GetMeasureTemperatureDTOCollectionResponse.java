package fr.neriumprod.meteodatasspringapp.graphql.response.measure;

import fr.neriumprod.meteodatasspringapp.dto.MeasureTemperatureDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
public class GetMeasureTemperatureDTOCollectionResponse {
    private boolean success;
    private String message;
    private Collection<MeasureTemperatureDTO> measureTemperatureDTOCollection;

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

    public Collection<MeasureTemperatureDTO> getMeasureTemperatureDTOCollection() {
        return measureTemperatureDTOCollection;
    }

    public void setMeasureTemperatureDTOCollection(Collection<MeasureTemperatureDTO> measureTemperatureDTOCollection) {
        this.measureTemperatureDTOCollection = measureTemperatureDTOCollection;
    }
}
