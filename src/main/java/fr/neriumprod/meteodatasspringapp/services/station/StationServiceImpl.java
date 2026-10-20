package fr.neriumprod.meteodatasspringapp.services.station;

import fr.neriumprod.meteodatasspringapp.dao.mongo.StationRepository;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import fr.neriumprod.meteodatasspringapp.graphql.input.station.StationInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetOneStationResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetStationCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.SaveStationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService{
    private StationRepository stationRepository;

    /**
     *
     * @param stationInput
     * @return the station saved on database.
     */
    public SaveStationResponse save(StationInput stationInput){
        Station station = new Station();
        station.setUserId(stationInput.getUserId());
        station.setFirmwareVersion(stationInput.getFirmwareVersion());
        station.setTotalMeasures(stationInput.getTotalMeasures());
        station.setStartServiceDate(LocalDateTime.now());
        station.setLastMeasureDate(LocalDateTime.now());

        Battery battery = new Battery(stationInput.getBatteryInput().getBatteryState(),
                stationInput.getBatteryInput().getOutputVoltage());
        station.setBattery(battery);

        Station stationResponse = stationRepository.save(station);
        return new SaveStationResponse(
                true,
                "Success to save this new station !",
                stationResponse);
    }

    /**
     *
     * @param id
     * @return the updated station with the new total measure and last measure date
     */
    public GetOneStationResponse newMeasureUpdated(String id){
        Station station = stationRepository.findById(id).orElse(null);
        if(station != null){
            station.setTotalMeasures(station.getTotalMeasures() + 1);
            station.setLastMeasureDate(LocalDateTime.now());
            Station updatedStation = stationRepository.save(station);
            return new GetOneStationResponse(
                    true,
                    "Success to update this station!",
                    updatedStation);
        }else{
            return new GetOneStationResponse(
                    false,
                    "No station saved with this ID on database.",
                    null);
        }
    }

    /**
     *
     * @param id, the station id
     * @return a Station with his datas
     */
    @Override
    public GetOneStationResponse findById(String id) {
        Station stationResponse = stationRepository.findById(id).orElse(null);
        if(stationResponse != null){
            return new GetOneStationResponse(
                    true,
                    "Success to get Station with the ID : " + id,
                    stationResponse);
        }else{
            return new GetOneStationResponse(
                    false,
                    "No Station saved with this ID.",
                    null);
        }
    }

    /**
     *
     * @return all stations saved on database
     */
    public GetStationCollectionResponse findAllStations(){
        Collection<Station> stationsResponse = stationRepository.findAll();
        if(stationsResponse.isEmpty()){
            return new GetStationCollectionResponse(
                    false,
                    "No station saved on database.",
                    Collections.emptyList());
        }else{
            return new GetStationCollectionResponse(
                    true,
                    "Success to return all stations saved on database.",
                    stationsResponse);
        }
    }

    /**
     *
     * @param id is the user ID
     * @return the Station collection detained by the user.
     */
    @Override
    public GetStationCollectionResponse findByUserId(Long id) {
        Collection<Station> stationsResponse = stationRepository.findByUserId(id);
        if(stationsResponse.isEmpty()){
            return new GetStationCollectionResponse(
                    false,
                    "No Station found with this user ID.",
                    Collections.emptyList());
        }else{
            return new GetStationCollectionResponse(
                    true,
                    "Success to find station(s) with this user ID : " + id,
                    stationsResponse);
        }
    }

    /**
     *
     * @param totalMeasures
     * @return all stations what saved fewer measures than @param
     */
    @Override
    public GetStationCollectionResponse findByTotalMeasuresLessThanEqual(Long totalMeasures) {
        Collection<Station> stationsResponse = stationRepository.findByTotalMeasuresLessThanEqual(totalMeasures);
        if(stationsResponse.isEmpty()){
            return new GetStationCollectionResponse(
                    false,
                    "No Station found with with a total measure fewer than " + totalMeasures,
                    Collections.emptyList());
        }else{
            return new GetStationCollectionResponse(
                    true,
                    "Success to find station(s) with a total measure fewer than : " + totalMeasures,
                    stationsResponse);
        }
    }

    /**
     *
     * @param totalMeasures
     * @return all stations what saved more measures than @param
     */
    @Override
    public GetStationCollectionResponse findByTotalMeasuresGreaterThanEqual(Long totalMeasures) {
        Collection<Station> stationsResponse = stationRepository.findByTotalMeasuresGreaterThanEqual(totalMeasures);
        if(stationsResponse.isEmpty()){
            return new GetStationCollectionResponse(
                    false,
                    "No Station found with with a total measure greater than " + totalMeasures,
                    Collections.emptyList());
        }else{
            return new GetStationCollectionResponse(
                    true,
                    "Success to find station(s) with a total measure greater than : " + totalMeasures,
                    stationsResponse);
        }
    }
}
