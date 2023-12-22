package com.example.useradvisormanagement.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.useradvisormanagement.domain.Advisor;
import com.example.useradvisormanagement.domain.Booking;
import com.example.useradvisormanagement.repository.AdvisorRepository;
import com.example.useradvisormanagement.repository.BookingRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@Service
public class AdvisorService {

	AdvisorRepository advisorRepo;
	BookingRepository bookingRepo;
	
	@Autowired
	public AdvisorService(AdvisorRepository advisorRepo, BookingRepository bookingRepo) {
		this.advisorRepo = advisorRepo;
		this.bookingRepo = bookingRepo;
	}
	
	public void loadDefaultAdvisors(String importAdvisorFile){
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Advisor> advisors = objectMapper.readValue(
                new File(importAdvisorFile),
                new TypeReference<List<Advisor>>() {}
            );
            advisorRepo.saveAll(advisors);
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
	}
	
   /**
    * Create a new Tour Object and persist it to the Database.
    *
    * @param title title
    * @param description description
    * @param blurb blurb
    * @param price price
    * @param duration duration
    * @param bullets bullets
    * @param keywords keywords
    * @param tourPackageName tour package name
    * @param difficulty difficulty
    * @param region region
    * @return Tour Entity
    */
   public Advisor createAdvisor(String advisorProfileURL, String advisorName, List<Booking> bookingList) {
		/*
		 * Booking booking =
		 * bookingRepo.findByBookingTime(advisorName).get(0).orElseThrow(()-> new
		 * RuntimeException("Advisor does not exist: " + advisor));
		 */
       return advisorRepo.save(new Advisor(advisorName, advisorProfileURL));
   }
   
   public long total() {
       return advisorRepo.count();
   }
}
