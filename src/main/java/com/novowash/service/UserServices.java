package com.novowash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novowash.dao.UserServicesDao;
import com.novowash.model.ServiceCost;

@Service
public class UserServices {

	@Autowired private UserServicesDao servicesDao;
	
	public List<com.novowash.model.Service> getAllServices() {
		return servicesDao.getAllServices();
	}
	
	public List<ServiceCost> getServicesCostById(long serviceId) {
		return servicesDao.getServicesCostById(serviceId);
	}
}
