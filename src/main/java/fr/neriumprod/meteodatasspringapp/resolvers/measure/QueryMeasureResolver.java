package fr.neriumprod.meteodatasspringapp.resolvers.measure;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetMeasuresCollectionResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.GetOneMeasureResponse;
import fr.neriumprod.meteodatasspringapp.services.measures.MeasureServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@AllArgsConstructor
public class QueryMeasureResolver{
    private final MeasureServiceImpl measureService;

    @QueryMapping
    public GetOneMeasureResponse getMeasureById(@Argument @NonNull String id) {
        return measureService.findById(id);
    }

    @QueryMapping
    public Collection<Measure> getAllMeasures() {
        return measureService.findAlls();
    }

    @QueryMapping
    public GetMeasuresCollectionResponse getMeasuresByThingId(@Argument @NonNull String thingId) {
        return measureService.findByThingId(thingId);
    }
}
