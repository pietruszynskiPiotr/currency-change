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

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static final String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{from}/{to}.json";

    public ExchangeRate exchange(String from, String to) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate
                .getForEntity(url, String.class, from, to);
        String body = entity.getBody();
        JSONObject jsonObject = new JSONObject(body);
        JSONArray names = jsonObject.names();
        String field1 = (String) names.get(0);
        String field2 = (String) names.get(1);
        Date parse;
        BigDecimal cost;
        if (field1.equals("date")) {
            parse = format.parse((String) jsonObject.get(field1));
            cost = (BigDecimal) jsonObject.get(field2);
        } else {
            parse = format.parse((String) jsonObject.get(field2));
            cost = (BigDecimal) jsonObject.get(field1);
        }
        return new ExchangeRate(parse, cost);
    }

}
