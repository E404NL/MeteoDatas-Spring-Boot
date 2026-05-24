package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.dao.mongo.MeasureRepository;
import fr.neriumprod.meteodatasspringapp.dto.MeasureTemperatureDTO;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Meteo;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasureTemperatureDTOCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetOneMeasureResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.SaveMeasureResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class MeasureServiceImpl implements MeasureService {
    private MeasureRepository measureRepository;

    /**
     *
     * @return absolutly ALL measures saved on the database
     */
    @Override
    public GetMeasuresCollectionResponse findAlls() {
        Collection<Measure> response = measureRepository.findAll();
        if(response.isEmpty()){
            return new GetMeasuresCollectionResponse(
                    false,
                    "No Measure saved",
                    null);
        }
        return new GetMeasuresCollectionResponse(
                true,
                "All Measures saved on database",
                response);
    }

    /**
     *
     * @param thingId, the id of the embedded system requesting
     * @return a Measure Collection on ALL Measures saved by one embedded system
     */
    @Override
    public GetMeasuresCollectionResponse findByThingId(String thingId) {
        Collection<Measure> measuresResponse = measureRepository.findByThingId(thingId);
        if(measuresResponse.isEmpty()){
            return new GetMeasuresCollectionResponse(
                    false,
                    "No measure saved under this ID.",
                    Collections.emptyList());
        }
        return new GetMeasuresCollectionResponse(
                true,
                "Success to get measures under the ID : " + thingId,
                measuresResponse);
    }

    /**
     *
     * @param start, the debut of the timelaps including datas
     * @param end, the end of the timelapes including datas
     * @return a Measure Collection on ALL Measures saved between two
     * determinated times
     */
    @Override
    public Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return measureRepository.findByTimestampBetween(start, end);
    }

    /**
     *
     * @param id, the id of the Measure saved
     * @return one specific Measure determinated by the measure id
     */
    @Override
    public GetOneMeasureResponse findById(String id) {
        Measure measure = measureRepository.findById(id).orElse(null);
        if(measure == null){
            return new  GetOneMeasureResponse(
                    false,
                    "No measure found with this id : " + id,
                    null);
        }
        return new GetOneMeasureResponse(
                true,
                "Success to get measure with this id : " + id,
                measure);
    }

    /**
     *
     * @param thingId, the id of the embedded system what's requesting
     * @param timeStart, the debut of the timelaps including datas
     * @param timeEnd, the end of the timelaps including datas
     * @return a Measure Collection with ALL measure saved by an embedded system
     * between two determinated times
     */
    public GetMeasuresCollectionResponse getMeasuresByThingIdAndDateTimeBetween(
            String thingId, LocalDateTime timeStart, LocalDateTime timeEnd){
        Collection<Measure> measuresResponse = measureRepository
                .findByThingIdAndTimestampBetween(thingId, timeStart, timeEnd);

        if(measuresResponse.isEmpty()){
            return new GetMeasuresCollectionResponse(
                    false,
                    "No measure saved under this ID in these times.",
                    Collections.emptyList());
        }
        return new GetMeasuresCollectionResponse(
                true,
                "Success to get measures under the ID : " + thingId
                        + " in these times : " + timeStart.toString() + " - " + timeEnd.toString(),
                measuresResponse);
    }

    /**
     *
     * @param thingId, the id of the embedded system what's requesting
     * @param timeStart, the debut of the timelaps including datas
     * @param timeEnd, the end of the timelaps including datas
     * @return a MeasureTemperatureDTO Collection including temperatures and
     * timestamp in measures saved by an embedded system between two determinated times
     */
    public GetMeasureTemperatureDTOCollectionResponse getTemperaturesByThingIdAndDateTimeBetween(
            String thingId, LocalDateTime timeStart, LocalDateTime timeEnd){
        Collection<MeasureTemperatureDTO> response = measureRepository
                .findTemperatureMeasurements(thingId, timeStart, timeEnd);

        if(response.isEmpty()){
            return new GetMeasureTemperatureDTOCollectionResponse(
                    false,
                    "No measures saved under this ID in these times",
                    Collections.emptyList());
        }
        return new GetMeasureTemperatureDTOCollectionResponse(
                true,
                "Success to get temperatures on measures under the ID : " + thingId
                        + " in these times : " + timeStart.toString() + " - " + timeEnd.toString(),
                response);
    }

    /**
     *
     * @param measureInput, the data in input to save as a Measure in DB
     * @return the Measure saved in database
     */
    @Override
    public SaveMeasureResponse save(MeasureInput measureInput) {
        Measure measure = new Measure();
        measure.setTimestamp(LocalDateTime.now());
        measure.setThingId(measureInput.getThingId());

        Battery battery = new Battery();
        if (measureInput.getBatteryInput() != null) {
            battery.setBatteryState(measureInput.getBatteryInput().getBatteryState());
            battery.setOutputVoltage(measureInput.getBatteryInput().getOutputVoltage());
        }
        measure.setBattery(battery);

        Meteo meteo = new Meteo();
        if (measureInput.getMeteoInput() != null) {
            meteo.setAirPressure(measureInput.getMeteoInput().getAirPressure());
            meteo.setTemperature(measureInput.getMeteoInput().getTemperature());
            meteo.setHumidityRate(measureInput.getMeteoInput().getHumidityRate());
        }
        measure.setMeteo(meteo);
        Measure persistedMeasure = measureRepository.save(measure);
        if (persistedMeasure == null) {
            return new SaveMeasureResponse(false,
                    "Measure is not saved because of an error",
                    null);
        }
        return new SaveMeasureResponse(true,
                "Measure registred with success !",
                persistedMeasure);
    }

    ///////////////////////////// DELETE ////////////////////////////////////

    /**
     *
     * @param measure, the Measure object to delete in DB
     * @return a DeleteResponse for confirmation
     */
    @Override
    public DeleteResponse delete(Measure measure) {
        measureRepository.delete(measure);
        return new DeleteResponse(true, "Success to delete measure");
    }

    /**
     *
     * @param measureId, the  Measure id for find the object to delete in DB
     * @return a DeleteResponse with the MeasureId for confirmation
     */
    @Override
    public DeleteResponse deleteById(String measureId) {
        measureRepository.deleteById(measureId);
        return new DeleteResponse(
                true,
                "Success to delete this measure : " + measureId);
    }

    /**
     *
     * @param thingId, the id of the embedded system what's requesting
     * @return a DeleteResponse with the thingId for confirmation
     */
    @Override
    public DeleteResponse deleteByThingId(String thingId) {
        measureRepository.deleteByThingId(thingId);
        return new DeleteResponse(
                true,
                "Success to delete measures under the ID : " + thingId);
    }

    /**
     *
     * @return a DeleteResponse for confirmation
     */
    public DeleteResponse deleteAll() {
        measureRepository.deleteAll();
        return new DeleteResponse(
                true,
                "All measures deleted successfully");
    }
}
