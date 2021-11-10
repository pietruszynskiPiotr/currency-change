package es.ulpgc.services;

import es.ulpgc.model.ExchangeRate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CurrencyRateService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final String ACTUAL_CURRENCY_URL = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{from}/{to}.json";

    public ExchangeRate exchange(String from, String to) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate
                .getForEntity(ACTUAL_CURRENCY_URL, String.class, from, to);
        String json = entity.getBody();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray names = jsonObject.names();
        String field1 = (String) names.get(0);
        String field2 = (String) names.get(1);
        Date parse;
        BigDecimal cost;
        if (field1.equals("date")) {
            parse = DATE_FORMAT.parse((String) jsonObject.get(field1));
            cost = new BigDecimal(jsonObject.get(field2).toString());
        } else {
            parse = DATE_FORMAT.parse((String) jsonObject.get(field2));
            cost = new BigDecimal(jsonObject.get(field2).toString());
        }
        return new ExchangeRate(parse, cost);
    }

}
