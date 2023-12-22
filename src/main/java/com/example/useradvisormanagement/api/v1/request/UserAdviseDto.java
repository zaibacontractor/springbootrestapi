package com.example.useradvisormanagement.api.v1.request;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserAdviseDto {
	
	@JsonProperty
    private String username;

	@JsonProperty
    private String email;

	@JsonProperty
    private String password;

	public UserAdviseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAdviseDto(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAdvise [ username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}
	

    // constructors, getters, setters
}
