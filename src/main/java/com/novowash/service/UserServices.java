package com.novowash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novowash.dao.impl.UserServicesDaoImpl;

@Service
public class UserServices {

	@Autowired private UserServicesDaoImpl servicesDaoImpl;
	
	public List<com.novowash.model.Service> getAllServices() {
		return servicesDaoImpl.getAllServices();
	}
}
