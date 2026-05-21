package fr.neriumprod.meteodatasspringapp.resolvers.measure;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.SaveMeasureResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.DeleteResponse;
import fr.neriumprod.meteodatasspringapp.services.measures.MeasureServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@Slf4j
public class MutationMeasureResolver{
        private final MeasureServiceImpl measureService;

        @MutationMapping
        public SaveMeasureResponse saveMeasure(@Argument @NonNull MeasureInput measureInput) {
                Measure persistedMeasure = measureService.save(measureInput);
                if (persistedMeasure == null) {
                        return new SaveMeasureResponse(false,
                                "Measure is not saved because of an error",
                                null);
                }
                return new SaveMeasureResponse(true,
                        "Measure registred with success !",
                        persistedMeasure);
        }

        @MutationMapping
        public DeleteResponse deleteAllMeasures() {
                return measureService.deleteAll();
        }
}
