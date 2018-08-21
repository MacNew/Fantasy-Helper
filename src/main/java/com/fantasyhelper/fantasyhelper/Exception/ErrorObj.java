package com.fantasyhelper.fantasyhelper.Exception;

import org.springframework.stereotype.Service;

@Service
public class ErrorObj {
    String erroMessage;
    String statusCode;

    public String getErroMessage() {
        return erroMessage;
    }

    public void setErroMessage(String erroMessage) {
        this.erroMessage = erroMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
