package es.ulpgc.controller;

import es.ulpgc.model.Currencies;
import es.ulpgc.services.CurrencyReaderService;
import es.ulpgc.services.CurrencyService;
import es.ulpgc.model.Exchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import static java.lang.String.format;

@RestController
@RequestMapping(value = "/currencies")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    private final CurrencyReaderService currencyReaderService;

    @GetMapping(value = "/{from}/{to}/{value}")
    public Exchange exchange(@PathVariable("from") String from,
                             @PathVariable("to") String to,
                             @PathVariable("value") BigDecimal value) {
        try {
            log.info(format("Exchange from %s to %s with amount %s.", from, to, value));
            return currencyService.exchange(from, to, value);
        } catch (ParseException e) {
            log.error(format("Parse exception happened: %s.", e.getMessage()), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exchange BAD REQUEST", e);
        } catch (HttpClientErrorException e) {
            log.error(format("Http client error exception happened: %s.", e.getMessage()), e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Exchange FORBIDDEN", e);
        }
    }

    @GetMapping
    public Currencies getCurrencies() {
        try {
            log.info("Get available currencies.");
            return currencyReaderService.read();
        } catch (IOException e) {
            log.error(format("Input/Output exception happened: %s.", e.getMessage()), e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Exchange FORBIDDEN", e);
        }
    }

}
