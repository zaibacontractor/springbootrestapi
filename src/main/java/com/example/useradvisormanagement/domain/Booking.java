package com.example.useradvisormanagement.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "Booking")// , schema = "codingmomma")
public class Booking {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDateTime bookingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisorId")
    private Advisor advisor;
        
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Booking(Long bookingId, LocalDateTime bookingTime, Advisor advisor, UserAdvise user) {
		super();
		this.bookingId = bookingId;
		this.bookingTime = bookingTime;
		this.advisor = advisor;
		this.user = user;
	}

	public Booking(Long bookingId, LocalDateTime bookingTime) {
		super();
		this.bookingId = bookingId;
		this.bookingTime = bookingTime;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Advisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}
      
	@ManyToOne(fetch = FetchType.LAZY)	  
	@JoinColumn(name = "userId") 
	private UserAdvise user;

	public UserAdvise getUser() {
		return user;
	}

	public void setUser(UserAdvise user) {
		this.user = user;
	}
	 	 
    // getters, setters, constructors        
}
