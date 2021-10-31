package es.ulpgc.controller;

import es.ulpgc.CurrencyService;
import es.ulpgc.model.Currency;
import es.ulpgc.model.Exchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/currencies")
public class CurrencyController {

    private CurrencyService currencyService;

    @GetMapping("/from/to/value")
    public Exchange getExchange(String from, String to, Integer value) {
        return currencyService.exchange(from, to, value);
    }

    @GetMapping
    public List<Currency> getCurrencies() throws IOException {
        return currencyService.getCurrencies();
    }

}
