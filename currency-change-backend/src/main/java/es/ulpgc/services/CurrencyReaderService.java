package es.ulpgc.services;

import es.ulpgc.model.Currency;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CurrencyReaderService {

    public Currency[] read() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("currency-change-backend/currencies.txt"));

        return strings.stream()
                .map(line -> line.split(", "))
                .map(arr -> {
                    String currency = arr[0];
                    String name = arr[1];
                    String unit = arr[2];
                    return new Currency(currency, name, unit);
                })
                .toArray(Currency[]::new);
    }

}
