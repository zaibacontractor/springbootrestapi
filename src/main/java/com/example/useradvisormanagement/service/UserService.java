package com.example.useradvisormanagement.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.useradvisormanagement.domain.Advisor;
import com.example.useradvisormanagement.domain.Booking;
import com.example.useradvisormanagement.domain.UserAdvise;
import com.example.useradvisormanagement.repository.AdvisorRepository;
import com.example.useradvisormanagement.repository.BookingRepository;
import com.example.useradvisormanagement.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@Service
public class UserService {

	UserRepository userRepo;
	BookingRepository bookingRepo;
	
	@Autowired
	public UserService(UserRepository userRepo, BookingRepository bookingRepo) {
		this.userRepo = userRepo;
		this.bookingRepo = bookingRepo;
	}
	
	public void loadDefaultUsers(String importUserFile){
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<UserAdvise> users = objectMapper.readValue(
                new File(importUserFile),
                new TypeReference<List<UserAdvise>>() {}
            );
            userRepo.saveAll(users);
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
	}
	
   
   public UserAdvise createUser(String userName, String email, String password) {
		/*
		 * Booking booking =
		 * bookingRepo.findByBookingTime(advisorName).get(0).orElseThrow(()-> new
		 * RuntimeException("Advisor does not exist: " + advisor));
		 */
       return userRepo.save(new UserAdvise(userName,email,password));
   }
   
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	// find by email
   public Optional<UserAdvise> findByEmail(String email) {
	   return (Optional) userRepo.findByEmail(email);
   }
//   find by id
   public UserAdvise findByID(Long id) { return userRepo.findById(id).orElse(null);}

//   register
   public UserAdvise register(UserAdvise user) {
       try {
           //        hash password
           //user.setPassword(passwordEncoder().encode(user.getPassword()));
           //        save user
           return userRepo.save(user);
           //        tạo quyền role
			/*
			 * UserRole userRole = new UserRole();
			 * userRole.setRoleID(RoleConstant.roleUser); userRole.setUserID(user.getId());
			 * userRoleRepository.save(userRole);
			 */
           
       }catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
       }
	
   }
   
   public long total() {
       return userRepo.count();
   }
}
