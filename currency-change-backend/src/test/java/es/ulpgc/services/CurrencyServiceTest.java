package es.ulpgc.services;

import es.ulpgc.model.Exchange;
import es.ulpgc.model.ExchangeRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyService.class)
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @MockBean
    private CurrencyRateService currencyRateService;
//
    @Test
    public void exchange_givenValidFromValidTo_shouldSucceed() throws ParseException {
        // given
        String from = "EUR";
        String to = "PLN";
        BigDecimal value = new BigDecimal("100");
        BigDecimal afterChange = new BigDecimal("464");
        ExchangeRate exchangeRate = new ExchangeRate(Date.valueOf("2021-11-12"), afterChange);
        Exchange expectedExchange = new Exchange(from, to, value, afterChange);
        given(currencyRateService.exchange(from, to))
                .willReturn(exchangeRate);

        // when
        Exchange actualExchange = currencyService.exchange(from, to, value);

        // then
        assertEquals(expectedExchange.getFrom(), actualExchange.getFrom());
        assertEquals(expectedExchange.getTo(), actualExchange.getTo());
        assertEquals(expectedExchange.getBeforeChange(), actualExchange.getBeforeChange());

        System.out.println("dupa");
    }

}
