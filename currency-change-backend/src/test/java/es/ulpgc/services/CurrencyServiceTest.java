package es.ulpgc.services;

import es.ulpgc.model.Exchange;
import es.ulpgc.model.ExchangeRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyService.class)
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @MockBean
    private CurrencyRateService currencyRateService;

    @Test
    public void exchange_givenValidPlnFromValidEurTo_shouldSucceed() throws ParseException {
        // given
        String from = "pln";
        String to = "eur";
        BigDecimal beforeExchange = new BigDecimal("464");

        ExchangeRate exchangeRate = new ExchangeRate(Date.valueOf("2021-11-12"), new BigDecimal("0.215541"));
        Exchange expectedExchange = new Exchange(from, to, beforeExchange, new BigDecimal("100.011024"));
        given(currencyRateService.exchange(from, to))
                .willReturn(exchangeRate);

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        assertEquals(expectedExchange.getFrom(), actualExchange.getFrom());
        assertEquals(expectedExchange.getTo(), actualExchange.getTo());
        assertEquals(expectedExchange.getBeforeChange(), actualExchange.getBeforeChange());
        assertEquals(expectedExchange.getAfterChange(), actualExchange.getAfterChange());
        verify(currencyRateService, times(1)).exchange(from, to);
        verifyNoMoreInteractions(currencyRateService);
    }

    @Test
    public void exchange_givenValidUsdFromValidJpyTo_shouldSucceed() throws ParseException {
        // given
        String from = "usd";
        String to = "jpy";
        BigDecimal beforeExchange = new BigDecimal("10");

        ExchangeRate exchangeRate = new ExchangeRate(Date.valueOf("2021-11-12"), new BigDecimal("114.042544"));
        Exchange expectedExchange = new Exchange(from, to, beforeExchange, new BigDecimal("1140.425440"));
        given(currencyRateService.exchange(from, to))
                .willReturn(exchangeRate);

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        assertEquals(expectedExchange.getFrom(), actualExchange.getFrom());
        assertEquals(expectedExchange.getTo(), actualExchange.getTo());
        assertEquals(expectedExchange.getBeforeChange(), actualExchange.getBeforeChange());
        assertEquals(expectedExchange.getAfterChange(), actualExchange.getAfterChange());
        verify(currencyRateService, times(1)).exchange(from, to);
        verifyNoMoreInteractions(currencyRateService);
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenValidFromInvalidTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "pln";
        String to = "nic";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenInvalidFromValidTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "nic";
        String to = "pln";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenInvalidFromInvalidTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "nic";
        String to = "cos";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenEmptyFromValidTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "";
        String to = "pln";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }


    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenValidFromEmptyTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "pln";
        String to = "";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenEmptyFromEmptyTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "";
        String to = "";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenNullFromValidTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = null;
        String to = "pln";
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }


    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenValidFromNullTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = "pln";
        String to = null;
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

    @Test(expected = HttpClientErrorException.class)
    public void exchange_givenNullFromNullTo_shouldThrowHttpClientErrorException() throws ParseException {
        // given
        String from = null;
        String to = null;
        BigDecimal beforeExchange = new BigDecimal("464");

        given(currencyRateService.exchange(from, to))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        // when
        Exchange actualExchange = currencyService.exchange(from, to, beforeExchange);

        // then
        // throw HttpClientErrorException
    }

}
