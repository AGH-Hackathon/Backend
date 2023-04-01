package edu.agh.twonhalffront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class GameApplication {

    private final static String SERVER_PORT_CONFIG = "server.port";
    private final static int SERVER_PORT = 8008;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GameApplication.class);
        app.setDefaultProperties(Collections.singletonMap(SERVER_PORT_CONFIG, SERVER_PORT));
        app.run(args);
    }
}
