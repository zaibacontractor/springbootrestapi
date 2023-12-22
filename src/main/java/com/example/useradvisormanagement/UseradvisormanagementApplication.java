package com.example.useradvisormanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.useradvisormanagement.domain.Booking;
import com.example.useradvisormanagement.service.AdvisorService;
import com.example.useradvisormanagement.service.BookingService;
import com.example.useradvisormanagement.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "AdvisorManagement API Documentation"))
public class UseradvisormanagementApplication implements CommandLineRunner{
	
	@Autowired
	private AdvisorService advisorService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	@Value("${ec.importAdvisorfile}")
    private String importAdvisorfile;
	
	@Value("${ec.importBookingfile}")
    private String importBookingfile;
	
	@Value("${ec.importUserfile}")
    private String importUserfile;

	public static void main(String[] args){

        SpringApplication.run(UseradvisormanagementApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		//advisorService.loadDefaultAdvisors(importAdvisorfile);
		long numOfAdvisors = advisorService.total();
		
		//userService.loadDefaultUsers(importUserfile);
		long numOfUsers = userService.total();
		System.out.println("Total number of Advisors="+numOfAdvisors);

		System.out.println("Total number of Bookings="+numOfUsers);
		
		bookingService.loadDefaultBookings(importBookingfile);
		long numOfBookings = bookingService.total();
		System.out.println("Total number of Bookings="+numOfBookings);		
	}
	/*
	private void createTourPackages() {
		tourPackService.createTourPackage("BC", "Backpack Cal");
        tourPackService.createTourPackage("CC", "California Calm");
        tourPackService.createTourPackage("CH", "California Hot springs");
        tourPackService.createTourPackage("CY", "Cycle California");
        tourPackService.createTourPackage("DS", "From Desert to Sea");
        tourPackService.createTourPackage("KC", "Kids California");
        tourPackService.createTourPackage("NW", "Nature Watch");
        tourPackService.createTourPackage("SC", "Snowboard Cali");
        tourPackService.createTourPackage("TC", "Taste of California");
	}

	private void createAdvisors(String fileToImport) throws IOException{
		AdvisorFromFile.read(fileToImport).forEach(importedAdvisor ->
			advisorService.createAdvisor(importedAdvisor.getAdvisorName(),
					importedAdvisor.getAdvisorProfileURL()
					);
	}
	
    private static class AdvisorFromFile {
        //fields
        private String advisorName, advisorProfileURL;
        //reader
        static List<AdvisorFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<AdvisorFromFile>>() {});
        }
        protected AdvisorFromFile(){}
        
        String getAdvisorName() { return advisorName; } 
        
        String getAdvisorProfileURL() { return advisorProfileURL; }
    }
    */
}