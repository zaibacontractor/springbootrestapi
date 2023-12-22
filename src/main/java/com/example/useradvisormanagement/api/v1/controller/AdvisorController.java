package com.example.useradvisormanagement.api.v1.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.useradvisormanagement.api.v1.request.BookingRequest;
import com.example.useradvisormanagement.domain.Advisor;
import com.example.useradvisormanagement.domain.Booking;
import com.example.useradvisormanagement.exception.ResourceNotFoundException;
import com.example.useradvisormanagement.repository.AdvisorRepository;

@RestController
@RequestMapping("/admin/advisors")
public class AdvisorController {
	
    @Autowired
    private AdvisorRepository advisorRepository;

    // GET endpoint to fetch all advisors
    @GetMapping
    public List<Advisor> getAllAdvisors() {
        return advisorRepository.findAll();
    }

    // POST endpoint to create a new advisor
    @PostMapping
    public ResponseEntity<Advisor> createAdvisor(@RequestBody Advisor advisor) {
    	System.out.println("Inside createAdvisor"+ advisor.toString());
          Advisor advisorObj = (Advisor) advisorRepository.save(advisor);
          System.out.println("Advisor created successfully"+ advisorObj.toString());
          if(advisorObj!=null && !advisorObj.getAdvisorName().isEmpty())
        	  	return  ResponseEntity.ok(advisorObj);
          else
        	   return ResponseEntity.badRequest().build();
    }

    // GET endpoint to fetch a specific advisor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Advisor> getAdvisorById(@PathVariable(value = "id") Long advisorId) throws Throwable {
        Advisor advisor = (Advisor) advisorRepository.findById(advisorId)
            .orElseThrow(() -> new ResourceNotFoundException("Advisor not found with id: " + advisorId));
        return ResponseEntity.ok().body(advisor);
    }

    // PUT endpoint to update an existing advisor
    @PutMapping("/{id}")
    public ResponseEntity<Advisor> updateAdvisor(
        @PathVariable(value = "id") Long advisorId, @RequestBody Advisor advisorDetails) throws Throwable {
        
        Advisor advisor = (Advisor) advisorRepository.findById(advisorId)
            .orElseThrow(() -> new ResourceNotFoundException("Advisor not found with id: " + advisorId));

        advisor.setAdvisorName(advisorDetails.getAdvisorName());
        advisor.setAdvisorProfileURL(advisorDetails.getAdvisorProfileURL());

        Advisor updatedAdvisor = (Advisor) advisorRepository.save(advisor);
        return ResponseEntity.ok(updatedAdvisor);
    }

    // DELETE endpoint to delete an advisor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdvisor(@PathVariable(value = "id") Long advisorId) throws Throwable {
        Advisor advisor = (Advisor) advisorRepository.findById(advisorId)
            .orElseThrow(() -> new ResourceNotFoundException(400,"Advisor not found with id: " + advisorId));

        advisorRepository.delete(advisor);
        return ResponseEntity.ok().build();
    }
    
    // POST endpoint to book an advisor at a specific time
    @PostMapping("/{advisorId}/book")
    public ResponseEntity<Advisor> bookAdvisor(
        @PathVariable(value = "advisorId") Long advisorId, @RequestBody BookingRequest bookingRequest) {
        
        Advisor advisor = advisorRepository.findById(advisorId)
            .orElseThrow(() -> new ResourceNotFoundException(400,"Advisor not found with id: " + advisorId));

        Booking newBooking = new Booking();
        newBooking.setBookingTime(bookingRequest.getBookingTime());
        newBooking.setAdvisor(advisor);

        advisor.getBookings().add(newBooking);
        advisorRepository.save(advisor);

        return ResponseEntity.ok(advisor);
    }
    
}
