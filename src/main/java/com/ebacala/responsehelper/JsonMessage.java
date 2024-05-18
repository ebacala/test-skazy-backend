package com.ebacala.responsehelper;

public class JsonMessage {
    private String body;

    public JsonMessage(String body) {
        this.body = "{\"message\": \"" + body + "\"}";
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
