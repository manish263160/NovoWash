package com.novowash.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.model.LoginPayload;
import com.novowash.model.Service;

@RestController
public class ServiceConttroller {

	@RequestMapping(method=RequestMethod.POST,value="/login")
	public Service add(@RequestBody LoginPayload loginObj) {
		return null;
	}	
}
