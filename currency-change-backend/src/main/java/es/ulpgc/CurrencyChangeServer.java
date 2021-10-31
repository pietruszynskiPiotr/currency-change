package es.ulpgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class CurrencyChangeServer {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyChangeServer.class);
    }

}
