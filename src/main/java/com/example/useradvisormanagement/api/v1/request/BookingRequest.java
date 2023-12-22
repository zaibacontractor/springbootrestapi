package com.example.useradvisormanagement.api.v1.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingRequest {
    private LocalDateTime bookingTime;

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

    
    // getters, setters
}
