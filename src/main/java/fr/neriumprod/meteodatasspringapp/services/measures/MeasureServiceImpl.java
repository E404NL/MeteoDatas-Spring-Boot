package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.dao.mongo.MeasureRepository;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Meteo;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetOneMeasureResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class MeasureServiceImpl implements MeasureService {
    private MeasureRepository measureRepository;

    @Override
    public Collection<Measure> findAlls() {
        return measureRepository.findAll();
    }

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

    @Override
    public Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return measureRepository.findByTimestampBetween(start, end);
    }

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

    @Override
    public Measure save(MeasureInput measureInput) {
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

        return measureRepository.save(measure);
    }

    @Override
    public DeleteResponse delete(Measure measure) {
        measureRepository.delete(measure);
        return new DeleteResponse(true, "Success to delete measure");
    }

    @Override
    public DeleteResponse deleteById(String measureId) {
        measureRepository.deleteById(measureId);
        return new DeleteResponse(
                true,
                "Success to delete this measure : " + measureId);
    }

    @Override
    public DeleteResponse deleteByThingId(String thingId) {
        measureRepository.deleteByThingId(thingId);
        return new DeleteResponse(
                true,
                "Success to delete measures under the ID : " + thingId);
    }

    public DeleteResponse deleteAll() {
        measureRepository.deleteAll();
        return new DeleteResponse(
                true,
                "All measures deleted successfully");
    }
}
