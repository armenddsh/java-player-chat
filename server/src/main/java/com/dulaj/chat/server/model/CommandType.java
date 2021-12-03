package com.dulaj.chat.server.model;

public enum CommandType {
    JOIN("join"),
    MESSAGE("message"),
    LEAVE("leave");

    CommandType(String join) {

    }
}
