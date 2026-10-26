package fr.neriumprod.meteodatasspringapp.services.station;

import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetOneStationResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetStationCollectionResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface StationService {
    GetOneStationResponse findById(String id);
    GetStationCollectionResponse findByUserId(Long id);
    GetStationCollectionResponse findByTotalMeasuresLessThanEqual(Long totalMeasures);
    GetStationCollectionResponse findByTotalMeasuresGreaterThanEqual(Long totalMeasures);
}
