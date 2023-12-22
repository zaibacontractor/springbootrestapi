package com.example.useradvisormanagement.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.useradvisormanagement.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	Booking findByBookingTime(LocalDateTime bookingTime);

}
