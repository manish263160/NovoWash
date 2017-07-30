package com.novowash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novowash.Enums.CommonEnums;
import com.novowash.dao.UserServicesDao;
import com.novowash.model.ServiceCost;
import com.novowash.model.ServiceEnquire;

@Service
public class UserServices {

	@Autowired
	private UserServicesDao servicesDao;

	public List<com.novowash.model.Service> getAllServices() {
		List<com.novowash.model.Service> services = servicesDao.getAllServices();
		if (services != null && services.size() > 0) {
			for (com.novowash.model.Service service : services) {
				if (CommonEnums.ServiceCategory.HOME.category() == service.getServiceCat()
						&& CommonEnums.ServiceType.BOOK.type() == service.getServiceType()) {
					service.setServiceCosts(getServicesCostById(service.getId()));
				}
			}
		}
		return services;
	}

	public List<ServiceCost> getServicesCostById(long serviceId) {
		return servicesDao.getServicesCostById(serviceId);
	}
	
	public void bookOrEnquireService(ServiceEnquire enquire) {
		servicesDao.bookOrEnquireService(enquire);
	}
}
