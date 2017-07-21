package com.novowash.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novowash.model.NovoWashException;
import com.novowash.service.UserAuthService;
import com.novowash.utils.ApplicationConstants;

/**
 * 
 * @author mukeshks
 *
 */
public class UserAuthFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(UserAuthFilter.class);
	
	private UserAuthService userAuthService;
	
	private List<String> urlExceptions = new ArrayList<String>();
	
	private Pattern forgotPswd = Pattern.compile("/users/password/[a-zA-Z0-9]{26}");

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		
		NovoWashException novoWashException = null;
		if (request!=null) {
			//String URI = request.getRequestURI();
			//String cntxtPth = request.getContextPath();
			String requestName = request.getPathInfo();
			//String qStr = request.getQueryString();
			
			if( (requestName!=null && urlExceptions.contains(requestName) ) || forgotPswd.matcher(requestName).matches()) {
				filterchain.doFilter(servletrequest, servletresponse);
			}
			else {
				String authToken = request.getHeader(ApplicationConstants.REQ_HEAD_AUTH_TOKEN);
				String deviceId = request.getHeader(ApplicationConstants.REQ_HEAD_DEVICE_ID);
				String userName = request.getHeader(ApplicationConstants.REQ_HEAD_USER_NAME);
				
				if (authToken == null || deviceId == null || userName == null) {
					novoWashException = new NovoWashException();
					novoWashException.setMessage("Header parameter is missing");
					novoWashException.setStatus("HEADER_MISSING");
					novoWashException.setDescription("Auth token,deviceId and clientId are mandatory in header.");
					novoWashException.setStatusCode(300);
					processError(request, response, novoWashException);
				} else {
					logger.info("AuthenticationFilter:doFilter() Validating api for deviceId:" + deviceId + " authToken:"+authToken); 
					if(userAuthService.validateUserAuth(authToken, deviceId, userName)) {
						filterchain.doFilter(servletrequest, servletresponse);
					} else {
						novoWashException = new NovoWashException();
						novoWashException.setMessage("You are not authorized");
						novoWashException.setStatus("UNAUTHORIZED");
						novoWashException.setDescription("Either auth token is not valid or expired.");
						novoWashException.setStatusCode(HttpStatus.UNAUTHORIZED.value());
						processError(request, response, novoWashException);
					}
				}
			}
		} else {
			novoWashException = new NovoWashException();
			novoWashException.setMessage("Bad Request");
			novoWashException.setStatus("BAD_REQUEST");
			novoWashException.setDescription("Seems to be request is not valid.");
			novoWashException.setStatusCode(HttpStatus.BAD_REQUEST.value());
			processError(request, response, novoWashException);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		userAuthService = context.getBean(UserAuthService.class);
		
		String urlException = filterConfig.getInitParameter("urlExceptions");
		urlExceptions = Arrays.asList(urlException.split("\\s*,\\s*"));
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @param exception
	 */
	private void processError(ServletRequest req, ServletResponse res, NovoWashException exception){
		HttpServletResponse response = (HttpServletResponse) res;
		
		try{
			// Set response content type
			response.setContentType("application/json");
			response.setStatus(exception.getStatusCode()); 
	        JSONObject jsonOut = new JSONObject(exception);
	        jsonOut.remove("stackTrace");
	        
	        PrintWriter out = response.getWriter();
	        out.write(jsonOut.toString());
		}catch(Exception ex){
			//ex.printStackTrace();
		}
	}
	
}
