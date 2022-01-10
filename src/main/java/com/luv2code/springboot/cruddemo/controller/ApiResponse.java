package com.luv2code.springboot.cruddemo.controller;

public class ApiResponse {

    private int status;
    private String message;
    private Object result;

    public ApiResponse(int status, String message, Object result){
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

}