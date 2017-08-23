package com.novowash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novowash.Enums.CommonEnums;
import com.novowash.dao.UserServicesDao;
import com.novowash.model.Mail;
import com.novowash.model.ServiceCategory;
import com.novowash.model.ServiceCost;
import com.novowash.model.ServiceEnquire;

@Service
public class UserServices {

	@Autowired private UserServicesDao servicesDao;
	@Autowired private VelocityEmailTemplateService velocityService;
	@Autowired private MailerService mailerService;
	
	public List<ServiceCategory> getAllServicesDetails() {
		return servicesDao.getAllServicesDetails();
	}
	
	public List<ServiceCategory> getAllServiceCategories() {
		return servicesDao.getAllServiceCategories();
	}
	
	public List<com.novowash.model.Service> getAllServicsByCatId(long categoryId) {
		return servicesDao.getAllServicesByCatId(categoryId);
	}
	
	public List<com.novowash.model.Service> getAllServices() {
		List<com.novowash.model.Service> services = servicesDao.getAllServices();
		if (services != null && services.size() > 0) {
			for (com.novowash.model.Service service : services) {
				if (CommonEnums.ServiceType.BOOK.type() == service.getServiceType()) {
					service.setServiceCosts(getServicesCostById(service.getId()));
				}
			}
		}
		return services;
	}

	public List<ServiceCost> getServicesCostById(long serviceId) {
		return servicesDao.getServicesCostById(serviceId);
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public void bookOrEnquireService(ServiceEnquire enquire) {
		servicesDao.bookOrEnquireService(enquire);
		if (enquire.getId() > 0) {
			com.novowash.model.Service service = servicesDao.getServiceById(enquire.getServiceId());
			if (null != service) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("serviceName", service.getServiceName());
				model.put("bookDate", enquire.getServiceDate());

				String emailMessageBody = velocityService.geContentFromTemplate(model, "email_Templates/bookService.vm");
				Mail mail = new Mail();
				mail.setMailTo(enquire.getEmail());
				mail.setMailSubject("Booking Confirmation");
				mail.setMailContent(emailMessageBody);
				mail.setContentType("text/html");
				mailerService.sendEmail(mail);
			}
		}
	}
}
