package es.ulpgc.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Exchange {

    private final String from;

    private final String to;

    private final Integer beforeChange;

    private final Integer afterChange;

}
