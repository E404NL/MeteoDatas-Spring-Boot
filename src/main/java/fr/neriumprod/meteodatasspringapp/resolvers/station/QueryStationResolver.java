package fr.neriumprod.meteodatasspringapp.resolvers.station;

import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetOneStationResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetStationCollectionResponse;
import fr.neriumprod.meteodatasspringapp.services.station.StationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class QueryStationResolver {
    private StationServiceImpl stationService;

    @QueryMapping
    public GetOneStationResponse getStationById(@Argument @NonNull String id){
        return stationService.findById(id);
    }

    @QueryMapping
    public GetStationCollectionResponse getAllStations(){
        return stationService.findAllStations();
    }

    @QueryMapping
    public GetStationCollectionResponse getStationsByUserId(@Argument @NonNull Long id){
        return stationService.findByUserId(id);
    }
}
