package com.minsu.dto;

public enum ResponseStatus {
    SUCCESS("성공"),
    FAIL("실패");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }
}
