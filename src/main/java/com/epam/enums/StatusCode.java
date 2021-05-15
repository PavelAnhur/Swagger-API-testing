package com.epam.enums;

public enum StatusCode {
    OK_200(200),
    SERVER_ERROR_500(500),
    ERROR_404(404);

    private final int value;

    StatusCode(final int statusCodeValue) {
        this.value = statusCodeValue;
    }

    public int getValue() {
        return value;
    }
}
