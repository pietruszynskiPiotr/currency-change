package es.ulpgc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Exchange {

    private String from;

    private String to;

    private BigDecimal beforeChange;

    private BigDecimal afterChange;

}