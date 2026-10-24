package com.esraa.springboot.todos.exception;

public class ExceptionResponses {
    private int status;
    private String message;
    private long timeStamp;

    public int getStatus() {
        return status;
    }

    public ExceptionResponses() {
    }

    public ExceptionResponses(String message, int status, long timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
