package fr.neriumprod.meteodatasspringapp;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Measure;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Meteo;
import fr.neriumprod.meteodatasspringapp.graphql.input.Battery.BatteryInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.meteo.MeteoInput;
import fr.neriumprod.meteodatasspringapp.services.measures.MeasureServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@SpringBootApplication
@AllArgsConstructor
@EnableTransactionManagement
public class MeteoDatasSpringAppApplication implements CommandLineRunner {

    // Services
    private final MeasureServiceImpl measureService;


    public static void main(String[] args) {
        SpringApplication.run(MeteoDatasSpringAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MeteoDatasSpringAppApplication app started.\n");
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
//            Measure measure = Measure.builder()
//                .timestamp(LocalDateTime.now())
//                .thingID("ESP-0001")
//                .meteo(Meteo.builder()
//                    .temperature(21.4f)
//                    .humidityRate(65.2f)
//                    .airPressure(2L)
//                    .build())
//                .battery(Battery.builder()
//                    .batteryState(72)
//                    .outputVoltage(8.7f)
//                    .build())
//                .build();
            MeasureInput measureInput = MeasureInput.builder()
                .thingId("ESP-001").
                meteo(MeteoInput.builder()
                    .temperature(21.4f)
                    .humidityRate(65.2f)
                    .airPressure(2L)
                    .build())
                .battery(BatteryInput.builder()
                    .batteryState(72)
                    .outputVoltage(8.7f)
                    .build())
                .build();
            Measure savedMeasure = measureService.save(measureInput);
            System.out.println(savedMeasure);
        };
    }
}
