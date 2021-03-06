package com.novowash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.Enums.RESPONSE_CODES;
import com.novowash.model.ResponseObject;
import com.novowash.model.ServiceEnquire;
import com.novowash.service.UserServices;
import com.novowash.utils.GenUtilities;

@RestController
@RequestMapping(value = "/services")
public class UserServiceController {

	@Autowired
	private UserServices services;
	
	@RequestMapping(method = RequestMethod.POST, value = "/all")
	public ResponseObject getAllServicesDetails() {
		return GenUtilities.getSuccessResponseObject(services.getAllServicesDetails(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/get/category")
	public ResponseObject getAllServiceCategories() {
		return GenUtilities.getSuccessResponseObject(services.getAllServiceCategories(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/get")
	public ResponseObject getAllCategory() {
		return GenUtilities.getSuccessResponseObject(services.getAllServices(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/get/{categoryId}")
	public ResponseObject getAllServiceByCatId(@PathVariable long categoryId) {
		return GenUtilities.getSuccessResponseObject(services.getAllServicsByCatId(categoryId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/get/cost/{serviceId}")
	public ResponseObject getServicesCostById(@PathVariable long serviceId) {
		return GenUtilities.getSuccessResponseObject(services.getServicesCostById(serviceId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());

	}

	@RequestMapping(method = RequestMethod.POST, value = "/book")
	public ResponseObject bookOrEnquireService(@RequestBody ServiceEnquire enquire) {
		services.bookOrEnquireService(enquire);
		return GenUtilities.getSuccessResponseObject(enquire, RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());

	}

}
