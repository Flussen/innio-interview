package com.innio.api.adapters.response;

public class StandardResponse<T> {
    private String message;
    private T data;

    public StandardResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public StandardResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    
}
