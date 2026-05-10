package fr.neriumprod.meteodatasspringapp.resolvers.measure;

import fr.neriumprod.meteodatasspringapp.dao.mongo.MeasureRepository;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@AllArgsConstructor
public class QueryMeasureResolver{
    private final MeasureRepository measureRepository;

    @QueryMapping
    public Measure getMeasureById(String id) {
        return measureRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Collection<Measure> getAllMeasures() {
        return measureRepository.findAll();
    }

    @QueryMapping
    public Collection<Measure> getMeasuresByThingId(String thingId) {
        return measureRepository.findByThingID(thingId);
    }

}
