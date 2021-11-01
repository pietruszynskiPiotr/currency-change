package es.ulpgc.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class Exchange {

    private final String from;

    private final String to;

    private final BigDecimal beforeChange;

    private final BigDecimal afterChange;

}