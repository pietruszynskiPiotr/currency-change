package es.ulpgc;

import es.ulpgc.model.Currency;
import es.ulpgc.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyReader currencyReader;

    @Autowired
    public CurrencyService(CurrencyReader currencyReader) {
        this.currencyReader = currencyReader;
    }

    public Exchange exchange(String from, String to, Integer value) {
        return null;
    }

    public List<Currency> getCurrencies() throws IOException {
        return currencyReader.read();
    }

}
