package es.ulpgc.services;

import es.ulpgc.model.Currencies;
import es.ulpgc.model.Exchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CurrencyChangeClient {

    private static final String CURRENCIES_URL = "http://localhost:8080/currencies";

    private static final String EXCHANGE_URL = "http://localhost:8080/currencies/{from}/{to}/{value}";

    private final RestTemplate restTemplate = new RestTemplate();

    public Currencies getCurrencies() {
        ResponseEntity<Currencies> currencies = restTemplate
                .getForEntity(CURRENCIES_URL, Currencies.class);
        return currencies.getBody();
    }

    public Exchange exchange(String from, String to, Double value) {
        ResponseEntity<Exchange> exchange = restTemplate
                .getForEntity(EXCHANGE_URL, Exchange.class, from.toLowerCase(), to.toLowerCase(), value);
        return exchange.getBody();
    }

}
