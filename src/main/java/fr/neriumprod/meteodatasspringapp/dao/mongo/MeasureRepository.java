package fr.neriumprod.meteodatasspringapp.dao.mongo;

import fr.neriumprod.meteodatasspringapp.dto.MeasureTemperatureDTO;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository("mongoMeasureRepository")
public interface MeasureRepository extends MongoRepository<Measure, String> {
    Collection<Measure> findByTimestampBetween(
            LocalDateTime start,
            LocalDateTime end);

    Collection<Measure> findByThingId(String thingID);

    void deleteByThingId(String thingID);

    Collection<Measure> findByThingIdAndTimestampBetween(
            String thingID,
            LocalDateTime start,
            LocalDateTime end);

    @Aggregation(pipeline = {
            "{ '$match': { 'thingId': ?0, 'timestamp': { $gte: ?1, $lte: ?2 } } }",
            "{ '$project': { 'timestamp': 1, 'temperature': '$meteo.temperature' } }"
    })
    Collection<MeasureTemperatureDTO> findTemperatureMeasurements(
            String thingID,
            LocalDateTime start,
            LocalDateTime end);

}
