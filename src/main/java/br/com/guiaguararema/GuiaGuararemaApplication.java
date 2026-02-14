package br.com.guiaguararema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GuiaGuararemaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuiaGuararemaApplication.class, args);
    }
}
