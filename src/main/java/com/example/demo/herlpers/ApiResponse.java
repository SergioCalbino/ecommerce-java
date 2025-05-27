package com.example.demo.herlpers;

import java.time.LocalDateTime;


public class ApiResponse<T> {
    private String message;
    private LocalDateTime timestamp;
    private int status;
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
