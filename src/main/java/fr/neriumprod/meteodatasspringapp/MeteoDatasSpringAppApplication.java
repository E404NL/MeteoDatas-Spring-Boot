package fr.neriumprod.meteodatasspringapp;

import fr.neriumprod.meteodatasspringapp.entities.postgres.User;
import fr.neriumprod.meteodatasspringapp.graphql.input.Battery.BatteryInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.measure.MeasureInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.meteo.MeteoInput;
import fr.neriumprod.meteodatasspringapp.graphql.input.user.UserInput;
import fr.neriumprod.meteodatasspringapp.graphql.response.measure.SaveMeasureResponse;
import fr.neriumprod.meteodatasspringapp.graphql.response.user.SaveUserResponse;
import fr.neriumprod.meteodatasspringapp.services.measures.MeasureServiceImpl;
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
            SaveMeasureResponse savedMeasure = measureService.save(measureInput);
            System.out.println(savedMeasure);

            UserInput userInput = UserInput.builder()
                    .firstName("Florent")
                    .lastName("Kopro")
                    .email("kopro.flowo@rienafoutre.cum")
                    .username("Konichiwa")
                    .password("*UwU_123456789*")
                    .birthDate(Calendar.getInstance().getTime())
                    .build();
            SaveUserResponse savedUser = userService.save(userInput);
            System.out.println(savedUser);
        };
    }
}
