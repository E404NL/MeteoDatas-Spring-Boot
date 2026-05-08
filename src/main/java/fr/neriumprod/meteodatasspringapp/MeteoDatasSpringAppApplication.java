package fr.neriumprod.meteodatasspringapp;

import fr.neriumprod.meteodatasspringapp.entities.mongo.Battery;
import fr.neriumprod.meteodatasspringapp.entities.mongo.Meteo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@AllArgsConstructor
@EnableTransactionManagement
public class MeteoDatasSpringAppApplication implements CommandLineRunner {

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

        };
    }
}
