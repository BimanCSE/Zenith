package com.tw.bootcamp.bookshop.user;

public class UserCreateResponse {

    private String email;
    private String emailValError = null;
    private String password;
    private String passwordValError = null;
    public UserCreateResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setPasswordValError(String passwordValError) {
        this.passwordValError = passwordValError;
    }

    public void setEmailValError(String emailValError) {
        this.emailValError = emailValError;
    }

    public String getEmail(){
        return this.email;
    }

    public String getEmailValError() {
        return emailValError;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordValError() {
        return passwordValError;
    }

    public String getPassword(){
        return this.password;
    }
}
