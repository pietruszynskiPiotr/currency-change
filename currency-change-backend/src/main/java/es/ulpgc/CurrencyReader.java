package es.ulpgc;

import es.ulpgc.model.Currency;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyReader {

    public List<Currency> read() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("currencies.txt"));

        return strings.stream()
                .map(line -> line.split(", "))
                .map(arr -> {
                    String currency = arr[0];
                    String name = arr[1];
                    String unit = arr[2];
                    return new Currency(currency, name, unit);
                })
                .collect(Collectors.toList());
    }

}
