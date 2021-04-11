package com.vacinagora.vacinagora_server.tile38;

import io.lettuce.core.protocol.ProtocolKeyword;

public enum Tile38Keywords implements ProtocolKeyword {

    NEARBY, FENCE, POINT, CIRCLE, SETHOOK, WITHIN, BOUNDS;

    private final byte name[];

    Tile38Keywords() {
        // cache the bytes for the command name. Reduces memory and cpu pressure when using commands.
        name = name().getBytes();
    }

    @Override
    public byte[] getBytes() {
        return name;
    }

}
