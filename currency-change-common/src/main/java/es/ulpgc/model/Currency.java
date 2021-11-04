package es.ulpgc.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Currency {

    private final String currency;

    private final String name;

    private final String unit;

}
