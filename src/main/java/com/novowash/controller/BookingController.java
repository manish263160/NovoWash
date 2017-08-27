package com.novowash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.Enums.RESPONSE_CODES;
import com.novowash.model.ResponseObject;
import com.novowash.service.BookingDetailsService;
import com.novowash.utils.GenUtilities;

@RestController@RequestMapping(value = "/booking")
public class BookingController {
	
	@Autowired private BookingDetailsService bookingservice;

	@RequestMapping(method = RequestMethod.POST, value = "/getAll")
	private ResponseObject getAllBookingDetails() {
		return GenUtilities.getSuccessResponseObject(bookingservice.getAllBooking(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
		
	}
}
