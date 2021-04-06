package com.vacinagora.vacinagora_server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Position {

    private String userId;

    private Double lat;

    private Double lng;

}

