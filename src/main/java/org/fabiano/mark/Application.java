package org.fabiano.mark;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Banking API",
                version = "1.0",
                description = "API para la gestión de entidades bancarias, cuentas, clientes, transacciones y empleados"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor local")
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
