package fr.neriumprod.meteodatasspringapp.resolvers.station;

import fr.neriumprod.meteodatasspringapp.graphql.input.station.StationInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetOneStationResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.SaveStationResponse;
import fr.neriumprod.meteodatasspringapp.services.station.StationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MutationStationResolver {
    private StationServiceImpl stationService;

    @MutationMapping
    public SaveStationResponse saveStation(@Argument @NonNull StationInput stationInput){
        return stationService.save(stationInput);
    }

    @MutationMapping
    public DeleteResponse deleteStationById(@Argument @NonNull String id){
        return stationService.deleteStationById(id);
    }

    @MutationMapping
    public void deleteAllStations(){
        stationService.deleteAllStations();
    }

    @MutationMapping
    public GetOneStationResponse changeUserStation(@Argument @NonNull String stationId, @Argument Long userId){
        return stationService.changeUserId(stationId, userId);
    }

    @MutationMapping
    public GetOneStationResponse checkTotalMeasures(@Argument @NonNull String stationId){
        return stationService.checkTotalMeasures(stationId);
    }
}
