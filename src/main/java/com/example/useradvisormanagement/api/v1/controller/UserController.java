package com.example.useradvisormanagement.api.v1.controller;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.useradvisormanagement.api.v1.request.BookingRequest;
import com.example.useradvisormanagement.api.v1.request.UserAdviseDto;
import com.example.useradvisormanagement.api.v1.response.AuthResponse;
import com.example.useradvisormanagement.config.JwtTokenUtil;
import com.example.useradvisormanagement.domain.Advisor;
import com.example.useradvisormanagement.domain.Booking;
import com.example.useradvisormanagement.domain.UserAdvise;
import com.example.useradvisormanagement.exception.ResourceNotFoundException;
import com.example.useradvisormanagement.repository.AdvisorRepository;
import com.example.useradvisormanagement.repository.UserRepository;
import com.example.useradvisormanagement.service.UserService;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/user/register")
public class UserController {
	private static final String SECRET_KEY = "your_secret_key_here"; // Replace with your secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepo;

    // GET endpoint to fetch all users
    @GetMapping
    public List<UserAdvise> getAllUserAdvises() {
        return userRepo.findAll();
    }
    

	/*
	 * public static String generateToken(UserDetails userDetails) { return
	 * Jwts.builder() .setSubject(userDetails.getUsername()) .setIssuedAt(new
	 * Date()) .setExpiration(new Date(System.currentTimeMillis() +
	 * EXPIRATION_TIME)) .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
	 * .compact(); }
	 */
    // POST endpoint to create a new user    
    @PostMapping
    public ResponseEntity<UserAdvise> createUser(@RequestBody UserAdvise user) {
          UserAdvise userObj = (UserAdvise) userRepo.save(user);
          System.out.println("User created successfully"+ userObj.toString());
          if(userObj!=null && !userObj.getUsername().isEmpty())
        	  	return  ResponseEntity.ok(userObj);
          else
        	   return (ResponseEntity<UserAdvise>) ResponseEntity.badRequest();
    }
    
    /*
    @PostMapping
    public ResponseEntity<UserAdvise> registerUser(@RequestBody UserAdviseDto userDto) {
    	UserAdvise userAdvise = new UserAdvise();
    	userAdvise.setEmail(userDto.getEmail());
    	userAdvise.setPassword(userDto.getPassword());
    	userAdvise.setUsername(userDto.getUsername());
        UserAdvise userObj = (UserAdvise) userService.register(userAdvise);
        System.out.println("User created successfully"+ userObj.toString());
        if(userObj!=null && !userObj.getUsername().isEmpty()) {
        	
        	 String jwtToken = JwtTokenUtil.generateToken(new UserDetail(userObj.getEmail(), userObj.getPassword()));

            // Return JWT token in the response
            return ResponseEntity.ok(new AuthResponse(jwtToken));
        	 //return (ResponseEntity<UserAdvise>) ResponseEntity.ok();
        }
        else
        	 return (ResponseEntity<UserAdvise>) ResponseEntity.badRequest();
    }
*/
    // GET endpoint to fetch a specific advisor by ID
	/*
	 * @GetMapping("/{id}") public ResponseEntity<Advisor>
	 * getAdvisorById(@PathVariable(value = "id") Long advisorId) throws Throwable {
	 * Advisor advisor = (Advisor) advisorRepository.findById(advisorId)
	 * .orElseThrow(() -> new
	 * ResourceNotFoundException("Advisor not found with id: " + advisorId)); return
	 * ResponseEntity.ok().body(advisor); }
	 * 
	 * // PUT endpoint to update an existing advisor
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<Advisor> updateAdvisor(
	 * 
	 * @PathVariable(value = "id") Long advisorId, @RequestBody Advisor
	 * advisorDetails) throws Throwable {
	 * 
	 * Advisor advisor = (Advisor) advisorRepository.findById(advisorId)
	 * .orElseThrow(() -> new
	 * ResourceNotFoundException("Advisor not found with id: " + advisorId));
	 * 
	 * advisor.setAdvisorName(advisorDetails.getAdvisorName());
	 * advisor.setAdvisorProfileURL(advisorDetails.getAdvisorProfileURL());
	 * 
	 * Advisor updatedAdvisor = (Advisor) advisorRepository.save(advisor); return
	 * ResponseEntity.ok(updatedAdvisor); }
	 * 
	 * // DELETE endpoint to delete an advisor
	 * 
	 * @DeleteMapping("/{id}") public ResponseEntity<?>
	 * deleteAdvisor(@PathVariable(value = "id") Long advisorId) throws Throwable {
	 * Advisor advisor = (Advisor) advisorRepository.findById(advisorId)
	 * .orElseThrow(() -> new
	 * ResourceNotFoundException(400,"Advisor not found with id: " + advisorId));
	 * 
	 * advisorRepository.delete(advisor); return ResponseEntity.ok().build(); }
	 * 
	 * // POST endpoint to book an advisor at a specific time
	 * 
	 * @PostMapping("/{advisorId}/book") public ResponseEntity<Advisor> bookAdvisor(
	 * 
	 * @PathVariable(value = "advisorId") Long advisorId, @RequestBody
	 * BookingRequest bookingRequest) {
	 * 
	 * Advisor advisor = advisorRepository.findById(advisorId) .orElseThrow(() ->
	 * new ResourceNotFoundException(400,"Advisor not found with id: " +
	 * advisorId));
	 * 
	 * Booking newBooking = new Booking();
	 * newBooking.setBookingTime(bookingRequest.getBookingTime());
	 * newBooking.setAdvisor(advisor);
	 * 
	 * advisor.getBookings().add(newBooking); advisorRepository.save(advisor);
	 * 
	 * return ResponseEntity.ok(advisor); }
	 */
}
