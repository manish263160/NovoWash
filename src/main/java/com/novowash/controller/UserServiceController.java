package com.novowash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.Enums.RESPONSE_CODES;
import com.novowash.model.ResponseObject;
import com.novowash.service.UserServices;
import com.novowash.utils.GenUtilities;

@RestController
@RequestMapping(value = "/services")
public class UserServiceController {
	
	@Autowired private UserServices services;
	
	@RequestMapping(method=RequestMethod.POST,value="/get")
	public ResponseObject getAllServices() {
		return GenUtilities.getSuccessResponseObject(services.getAllServices(), RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
	}	
	
	@RequestMapping(method=RequestMethod.POST,value="/get/cost/{serviceId}")
	public ResponseObject getServicesCostById(@PathVariable long serviceId) {
		return GenUtilities.getSuccessResponseObject(services.getServicesCostById(serviceId), RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
		
	}
	
}
