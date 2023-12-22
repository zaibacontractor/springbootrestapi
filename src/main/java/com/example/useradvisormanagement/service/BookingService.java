package com.example.useradvisormanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.useradvisormanagement.domain.Booking;
import com.example.useradvisormanagement.repository.BookingRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@Service
public class BookingService {
    private BookingRepository bookingRepo;

    @Autowired
    public BookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public void loadDefaultBookings(String importBookingsFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Booking> bookings = objectMapper.readValue(
                new File(importBookingsFile),
                new TypeReference<List<Booking>>() {}
            );
            bookingRepo.saveAll(bookings);
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
    
    /**
     * Create a Booking
     *
     * @param code code of the package
     * @param name name of the package
     *
     * @return new or existing tour package
     
    public Booking createBooking(Long bookingId, LocalDateTime bookingTime) {
        System.out.println("Code="+bookingId+" Time="+bookingTime);
        Booking bookingObj = new Booking(bookingId,bookingTime);
        return bookingRepo.
        		findById(bookingId).orElse(bookingRepo.save(bookingObj));
    }
    */

    /**
     * Lookup All Tour packages
     *
     * @return
     */
    public ArrayList<Booking> lookup(){
        System.out.println("Getting all Booking");
        return (ArrayList<Booking>) bookingRepo.findAll();
    }

    public long total() {
        return bookingRepo.count();
    }
}
