package com.example.useradvisormanagement.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Entity
@Table(name = "Advisor")//, schema = "codingmomma")
public class Advisor {
    public Advisor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="advisorId")
    private Integer advisorId = 0;

    @Column(name="advisorName")
    private String advisorName;
    
    @Column(name="advisorProfileURL")
    private String advisorProfileURL;

    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

	public Advisor(Integer advisorId, String advisorName, String advisorProfileURL) {
		super();
		this.advisorId = advisorId;
		this.advisorName = advisorName;
		this.advisorProfileURL = advisorProfileURL;
	}

	public Advisor(String advisorName, String advisorProfileURL) {
		super();
		this.advisorName = advisorName;
		this.advisorProfileURL = advisorProfileURL;
	}

	public int getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}

	public String getAdvisorProfileURL() {
		return advisorProfileURL;
	}

	public void setAdvisorProfileURL(String advisorProfileURL) {
		this.advisorProfileURL = advisorProfileURL;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Advisor [advisorId=" + advisorId + ", advisorName=" + advisorName + ", advisorProfileURL="
				+ advisorProfileURL + ", bookings=" + bookings + "]";
	}

	
    // getters, setters, and methods to handle bookings
	
}
