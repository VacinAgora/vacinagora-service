package com.vacinagora.vacinagora_server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Place {

    private String placeId;

    private Double latitude;

    private Double longitude;

    private String size;

}
