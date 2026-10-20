package fr.neriumprod.meteodatasspringapp.dao.mongo;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("mongoStationRepository")
public interface StationRepository extends MongoRepository<Station, String> {
    Collection<Station> findByUserId(Long id);
    Collection<Station> findByTotalMeasuresLessThanEqual(Long totalMeasures);
    Collection<Station> findByTotalMeasuresGreaterThanEqual(Long totalMeasures);
}
