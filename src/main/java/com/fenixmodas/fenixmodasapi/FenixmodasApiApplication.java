package com.fenixmodas.fenixmodasapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Fênix Modas API", description = "Uma API para controle de estoque e caixa", version = "1.0"))
public class FenixmodasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FenixmodasApiApplication.class, args);
    }

}
