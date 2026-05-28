package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetOneMeasureResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.SaveMeasureResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public interface MeasureService {
    GetMeasuresCollectionResponse findAlls();
    Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    GetMeasuresCollectionResponse findByThingId(String thingId);
    GetOneMeasureResponse findById(String id);
    SaveMeasureResponse save(MeasureInput measureInput);
    DeleteResponse delete(Measure measure);
    DeleteResponse deleteById(String id);
    DeleteResponse deleteByThingId(String thingId);
}
