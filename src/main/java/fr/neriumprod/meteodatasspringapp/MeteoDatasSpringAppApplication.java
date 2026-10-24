package fr.neriumprod.meteodatasspringapp;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Station;
import fr.neriumprod.meteodatasspringapp.entities.postgres.User;
import fr.neriumprod.meteodatasspringapp.graphql.input.Battery.BatteryInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.meteo.MeteoInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.station.StationInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.user.UserInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.SaveMeasureResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.station.SaveStationResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.SaveUserResponse;
import fr.neriumprod.meteodatasspringapp.services.measures.MeasureServiceImpl;
import fr.neriumprod.meteodatasspringapp.services.station.StationServiceImpl;
import fr.neriumprod.meteodatasspringapp.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
@EnableTransactionManagement
public class MeteoDatasSpringAppApplication implements CommandLineRunner {

    // Services
    private final MeasureServiceImpl measureService;
    private final UserServiceImpl userService;
    private final StationServiceImpl stationService;


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
//            UserInput userInput = UserInput.builder()
//                    .firstName("Florent")
//                    .lastName("Kopro")
//                    .email("kopro.flowo@rienafoutre.cum")
//                    .username("Konichiwa")
//                    .password("*UwU_123456789*")
//                    .birthDate(Calendar.getInstance().getTime())
//                    .build();
//            SaveUserResponse savedUser = userService.save(userInput);
//            System.out.println(savedUser);
//
//            BatteryInput batteryInput = BatteryInput.builder()
//                    .batteryState(72)
//                    .outputVoltage(8.7f)
//                    .build();
//
//            StationInput stationInput = StationInput.builder()
//                    .userId(savedUser.getUser().getId())
//                    .totalMeasures(0L)
//                    .firmwareVersion("V0.1")
//                    .batteryInput(batteryInput)
//                    .build();
//            SaveStationResponse savedStation = stationService.save(stationInput);
//            System.out.println(savedStation);
//
//            MeasureInput measureInput = MeasureInput.builder()
//                .thingId(savedStation.getStation().getId()).
//                meteo(MeteoInput.builder()
//                    .temperature(21.4f)
//                    .humidityRate(65.2f)
//                    .airPressure(2L)
//                    .build())
//                .battery(batteryInput)
//                .build();
//            SaveMeasureResponse savedMeasure = measureService.save(measureInput);
//            System.out.println(savedMeasure);
        };
    }
}
