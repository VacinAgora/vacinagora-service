package com.vacinagora.vacinagora_server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PositionEvent {

    private String command;

    private String group;

    private String detect;

    private String hook;

    private String key;

    private String time;

    private String id;

//    private String object;

}

