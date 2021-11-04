package es.ulpgc.services;

import es.ulpgc.model.Currencies;
import es.ulpgc.model.ExchangeRate;
import es.ulpgc.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

@Service
public class CurrencyService {

    private final CurrencyReaderService currencyReader;

    private final CurrencyRateService currencyRateService;

    @Autowired
    public CurrencyService(CurrencyReaderService currencyReader,
                           CurrencyRateService currencyRateService) {
        this.currencyReader = currencyReader;
        this.currencyRateService = currencyRateService;
    }

    public Exchange exchange(String from, String to, BigDecimal value) throws ParseException {
        ExchangeRate exchange = currencyRateService.exchange(from, to);
        BigDecimal afterExchange = exchange.getValue().multiply(value);
        return new Exchange(from, to, value, afterExchange);
    }

    public Currencies getCurrencies() throws IOException {
        return new Currencies(currencyReader.read());
    }

}
