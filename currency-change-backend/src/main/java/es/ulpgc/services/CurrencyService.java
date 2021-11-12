package es.ulpgc.services;

import es.ulpgc.model.ExchangeRate;
import es.ulpgc.model.Exchange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRateService currencyRateService;

    public Exchange exchange(String from, String to, BigDecimal value) throws ParseException {
        ExchangeRate exchange = currencyRateService.exchange(from, to);
        BigDecimal afterExchange = exchange
                .getValue()
                .multiply(value);
        return new Exchange(from, to, value, afterExchange);
    }

}
