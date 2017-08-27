package com.novowash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.model.BookingDetails;
import com.novowash.service.BookingDetailsService;

@RestController@RequestMapping(value = "/booking")
public class BookingController {
	
	@Autowired private BookingDetailsService bookingservice;

	@RequestMapping(method = RequestMethod.POST, value = "/getAll")
	private List<BookingDetails> getAllBookingDetails() {
		return bookingservice.getAllBooking();
		
	}
}
