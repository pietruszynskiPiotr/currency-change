package es.ulpgc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Currency {

    private String currency;

    private String name;

    private String unit;

}
