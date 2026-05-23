package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetOneMeasureResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public interface MeasureService {
    public Collection<Measure> findAlls();
    public Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    public GetMeasuresCollectionResponse findByThingId(String thingId);
    public GetOneMeasureResponse findById(String id);
    public Measure save(MeasureInput measureInput);
    public DeleteResponse delete(Measure measure);
    public DeleteResponse deleteById(String id);
    public DeleteResponse deleteByThingId(String thingId);
}
