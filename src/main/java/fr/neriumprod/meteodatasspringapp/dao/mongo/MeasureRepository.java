package fr.neriumprod.meteodatasspringapp.dao.mongo;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresByThingIdResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository("mongoMeasureRepository")
public interface MeasureRepository extends MongoRepository<Measure, String> {
    Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    Collection<Measure> findByThingId(String thingID);
}
