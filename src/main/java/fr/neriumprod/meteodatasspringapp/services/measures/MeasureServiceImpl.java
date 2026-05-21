package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.dao.mongo.MeasureRepository;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Meteo;
import fr.neriumprod.meteodatasspringapp.graphql.input.Battery.BatteryInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class MeasureServiceImpl implements MeasureService {
    private MeasureRepository measureRepository;

    @Override
    public Collection<Measure> findAlls() {
        return measureRepository.findAll();
    }

    @Override
    public Collection<Measure> findByThingId(String thingId) {
        return measureRepository.findByThingId(thingId);
    }

    @Override
    public Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return measureRepository.findByTimestampBetween(start, end);
    }

    @Override
    public Measure findById(String id) {
        return measureRepository.findById(id).orElse(null);
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
    public void delete(Measure measure) {
        measureRepository.delete(measure);
    }

    @Override
    public void deleteById(String id) {
        measureRepository.deleteById(id);
    }

    public DeleteResponse deleteAll() {
        measureRepository.deleteAll();
        return new DeleteResponse(true, "All measures deleted successfully");
    }
}
