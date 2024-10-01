package com.oversee.model;

public class Response {

    private String message;
    private String status;

    public Response(String message,  String status) {
        this.message = message;
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    //public void setStatus(String status) { this.status = status; }

    public String getMessage()
    {
        return message;
    }
    //public void setMessage(String message) { this.message = message; }
}
