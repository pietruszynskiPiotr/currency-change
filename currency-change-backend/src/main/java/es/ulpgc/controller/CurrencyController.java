package es.ulpgc.controller;

import es.ulpgc.model.Currencies;
import es.ulpgc.services.CurrencyService;
import es.ulpgc.model.Exchange;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/{from}/{to}/{value}")
    public Exchange getExchange(@PathVariable("from") String from,
                                @PathVariable("to") String to,
                                @PathVariable("value") BigDecimal value) throws ParseException {
        return currencyService.exchange(from, to, value);
    }

    @GetMapping
    public Currencies getCurrencies() throws IOException {
        return currencyService.getCurrencies();
    }

}
