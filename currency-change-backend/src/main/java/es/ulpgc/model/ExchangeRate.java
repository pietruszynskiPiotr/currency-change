package es.ulpgc.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public class ExchangeRate {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date date;

    private final BigDecimal value;

}
