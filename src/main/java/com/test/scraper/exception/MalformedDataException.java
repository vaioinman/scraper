package com.test.scraper.exception;


import lombok.Getter;

public class MalformedDataException extends Exception {
    @Getter
    private String text;
    private Exception originalException;

    public MalformedDataException(String text, Exception originalException) {
        this.text = text;
        this.originalException = originalException;
    }

    public MalformedDataException(String text) {
        this.text = text;
    }

    public void printStacktrace() {
        if (originalException != null) {
            originalException.printStackTrace();
        }
    }
}
