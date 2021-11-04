package es.ulpgc.services;

import es.ulpgc.model.Currencies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CurrencyChangeClient {

    private static final String CURRENCIES_URL = "http://localhost:8080/currencies";

    public Currencies getCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
//        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        ResponseEntity<Currencies> currencies = restTemplate
                .getForEntity(CURRENCIES_URL, Currencies.class);
        return new Currencies(currencies.getBody().getCurrencies());
    }

}
