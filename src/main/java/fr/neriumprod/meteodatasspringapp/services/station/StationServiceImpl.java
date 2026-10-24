package fr.neriumprod.meteodatasspringapp.services.station;

import fr.neriumprod.meteodatasspringapp.dao.mongo.MeasureRepository;
import fr.neriumprod.meteodatasspringapp.dao.mongo.StationRepository;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import fr.neriumprod.meteodatasspringapp.graphql.input.station.StationInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetOneStationResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.GetStationCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.SaveStationResponse;
import fr.neriumprod.meteodatasspringapp.services.measures.MeasureServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService{
    private StationRepository stationRepository;
    private MeasureRepository measureRepository;

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
     * @return DeleteResponse to confirm
     */
    public DeleteResponse deleteStationById(String id){
        Station station = stationRepository.findById(id).orElse(null);
        if(station != null){
            stationRepository.delete(station);
            return new DeleteResponse(true, "Success to delete the station with the ID " + id);
        }
        return new DeleteResponse(false, "No station found with this ID to delete");
    }

    /**
     * Delete all stations in database
     */
    public void deleteAllStations(){
        stationRepository.deleteAll();
    }

    /**
     *
     * @param stationId
     * @param userId
     * @return the station with new userId updated
     */
    public GetOneStationResponse changeUserId(String stationId, Long userId){
        Station station = stationRepository.findById(stationId).orElse(null);
        if(station != null){
            station.setUserId(userId);
            Station stationUpdated = stationRepository.save(station);
            return new GetOneStationResponse(
                    true,
                    "Success to update user on this station!",
                    stationUpdated);
        }
        return new GetOneStationResponse(
                false,
                "No station saved in database with this ID.",
                null);
    }

    /**
     *
     * @param stationId
     * @return the station with the totalMeasures updated
     */
    public GetOneStationResponse checkTotalMeasures(String stationId){
        Station station = stationRepository.findById(stationId).orElse(null);
        if(station != null){
            Collection<Measure> measures = measureRepository.findByThingId(stationId);
            Long nbStationMeasures = station.getTotalMeasures();
            int nbMeasuresSaved = measures.size();
            if(nbStationMeasures != nbMeasuresSaved){
                station.setTotalMeasures((long) nbMeasuresSaved);
                Station updatedStation = stationRepository.save(station);
                return new GetOneStationResponse(
                        true,
                        "Station " + stationId + " is updated on total measures : " + nbMeasuresSaved,
                        updatedStation);
            }else if(nbMeasuresSaved == nbStationMeasures){
                return new GetOneStationResponse(
                        false,
                        "The total measure is already updated.",
                        station);
            }
        }
        return new GetOneStationResponse(
                false,
                "No Station found with this ID.",
                null);
    }

    /**
     *
     * @param id, the station id
     * @return a Station with his data's
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
