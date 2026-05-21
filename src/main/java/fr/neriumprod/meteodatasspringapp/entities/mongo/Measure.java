package fr.neriumprod.meteodatasspringapp.entities.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Document(collection = "measure")
public class Measure {
    @Id
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;  // local time at the measure POST request

    private String thingId;     // embedded system ID what's post the request

    private Meteo meteo;    // meteo datas during the measure

    private Battery battery;    // battery state during the measure

}
