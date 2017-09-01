package com.novowash.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	private static final Logger logger = Logger.getLogger(UserServices.class);
	
	@Value("${sms.url}")
	private String smsServiceUrl;
	
	@Value("${sms.authkey}")
	private String smsauthkey;
	
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
		String sender="NOVOWS";
		if (enquire.getId() > 0) {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date curdate=new Date();
			String serviceDat = sdf.format(enquire.getServiceDate());
			
			com.novowash.model.Service service = servicesDao.getServiceById(enquire.getServiceId());
			String sms="Thanks you for connecting with NovoWash. You have requested for "+service.getServiceName()+" on "
					+serviceDat+".";
					
			if (null != service) {
				
				try {
					//Your authentication key
		            String authkey = smsauthkey;
		            //Multiple mobiles numbers separated by comma
		            String mobiles = enquire.getPhone();
		            //Sender ID,While using route4 sender id should be 6 characters long.
		            String senderId = sender;
		            //Your message to send, Add URL encoding here.
		            String message = sms;
		            //define route
		            String route="default";
					URLConnection myURLConnection=null;
					URL myURL=null;
		            BufferedReader reader=null;
		            String encoded_message=URLEncoder.encode(message);
		            StringBuilder sbPostData=new StringBuilder(smsServiceUrl);
					
		            sbPostData.append("authkey="+authkey);
		            sbPostData.append("&mobiles="+mobiles);
		            sbPostData.append("&message="+encoded_message);
		            sbPostData.append("&route="+route);
		            sbPostData.append("&sender="+senderId);
		            myURL = new URL(sbPostData.toString());
		            logger.info("url printed is==="+myURL.toString());
		            myURLConnection = myURL.openConnection();
		            myURLConnection.connect();
		            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		            String response;
	                while ((response = reader.readLine()) != null)
	                //print response
	                	logger.info("sms ent is done==="+response);

	                //finally close connection
	                reader.close();
				} catch (Exception e) {
					logger.info("Exception on sms sending"+e);
				}
				
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
	
	/*public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;  
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri;
    }*/
}
