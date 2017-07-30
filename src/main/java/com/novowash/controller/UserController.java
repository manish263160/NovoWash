package com.novowash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.model.LoginPayload;
import com.novowash.model.User;
import com.novowash.service.UserService;

@RestController
public class UserController {
	
	@Autowired private UserService userservice;
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public User login(@RequestBody LoginPayload loginObj) {
		return userservice.userLogin(loginObj.getUserName(), loginObj.getPassword(),loginObj.getDeviceid());
	}	

}
