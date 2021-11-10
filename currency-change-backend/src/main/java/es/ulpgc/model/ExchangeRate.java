package es.ulpgc.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public class ExchangeRate {

    private final Date date;

    private final BigDecimal value;

}
