package es.ulpgc.controller;

import es.ulpgc.services.CurrencyService;
import es.ulpgc.model.Currency;
import es.ulpgc.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/currencies")
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{from}/{to}/{value}")
    public Exchange getExchange(@PathVariable("from") String from,
                                @PathVariable("to") String to,
                                @PathVariable("value") BigDecimal value) throws ParseException {
        return currencyService.exchange(from, to, value);
    }

    @GetMapping
    public List<Currency> getCurrencies() throws IOException {
        return currencyService.getCurrencies();
    }

}
