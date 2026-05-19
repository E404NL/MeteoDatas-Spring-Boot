package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.dao.mongo.MeasureRepository;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
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
        return measureRepository.findByThingID(thingId);
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
    public Measure save(Measure measure) {
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
}
