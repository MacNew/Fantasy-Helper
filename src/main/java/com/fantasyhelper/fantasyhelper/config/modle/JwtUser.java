package com.fantasyhelper.fantasyhelper.config.modle;

public class JwtUser {
    private  String userName;
    private  String password;
    private String role;
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public JwtUser() {}

    public JwtUser(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }



    public String getRole() {
        return role;
    }
}
