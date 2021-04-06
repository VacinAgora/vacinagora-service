package com.vacinagora.vacinagora_server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceDto {

    private String placeId;

    private Object geometry;

}
