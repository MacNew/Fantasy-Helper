package com.fantasyhelper.fantasyhelper.modle;

import org.springframework.stereotype.Component;

@Component
public class JwtToken {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
