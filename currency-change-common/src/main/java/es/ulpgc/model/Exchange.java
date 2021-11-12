package es.ulpgc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Exchange {

    private String from;

    private String to;

    private BigDecimal beforeChange;

    private BigDecimal afterChange;

}