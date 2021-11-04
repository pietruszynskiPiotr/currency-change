package es.ulpgc.model;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Exchange {

    private final String from;

    private final String to;

    private final BigDecimal beforeChange;

    private final BigDecimal afterChange;

}