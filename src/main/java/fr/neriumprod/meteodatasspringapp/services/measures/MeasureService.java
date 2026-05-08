package fr.neriumprod.meteodatasspringapp.services.measures;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public interface MeasureService {
    public Collection<Measure> findAlls();
    public Collection<Measure> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    public Measure findByThingId(String thingId);
    public Measure findById(String id);
    public Measure save(Measure measure);
    public void delete(Measure measure);
    public void deleteById(String id);
}
