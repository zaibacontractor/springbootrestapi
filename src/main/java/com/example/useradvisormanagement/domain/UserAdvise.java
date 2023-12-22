package com.example.useradvisormanagement.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "[UserAdvise]") //, schema = "codingmomma")
public class UserAdvise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

	public UserAdvise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAdvise(Long userId, String username, String email, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public UserAdvise(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		return "UserAdvise [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}
	

    // constructors, getters, setters
}
