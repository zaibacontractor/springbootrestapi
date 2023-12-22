package com.example.useradvisormanagement.api.v1.response;

public class AuthResponse {
    private String token;
    // Any other additional fields you want to include in the response

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters and setters for 'token' and any additional fields
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    // Other getters and setters for additional fields (if any)
}
