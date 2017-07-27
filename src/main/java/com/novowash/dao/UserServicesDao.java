package com.novowash.dao;

import java.util.List;

import com.novowash.model.Service;
import com.novowash.model.ServiceCost;

public interface UserServicesDao {
	
	public List<Service> getAllServices();
	
	public List<ServiceCost> getServicesCostById(long serviceId);

}
