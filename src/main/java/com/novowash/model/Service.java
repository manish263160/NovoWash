package com.novowash.model;

/**
 * 
 * @author mukeshks
 *
 */
public class Service extends BaseDto {
	
	private long id;
	private String serviceName;
	private String serviceDesc;
	private String imgUrl;
	private int serviceCat; //(1-Home, 0-office) 
	private int serviceType; //(Enquire-1, Book-0)
	private int status;
	
	private ServiceCost serviceCost;
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the serviceDesc
	 */
	public String getServiceDesc() {
		return serviceDesc;
	}
	/**
	 * @param serviceDesc the serviceDesc to set
	 */
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * @return the serviceCat
	 */
	public int getServiceCat() {
		return serviceCat;
	}
	/**
	 * @param serviceCat the serviceCat to set
	 */
	public void setServiceCat(int serviceCat) {
		this.serviceCat = serviceCat;
	}
	/**
	 * @return the serviceType
	 */
	public int getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return the serviceCost
	 */
	public ServiceCost getServiceCost() {
		return serviceCost;
	}
	/**
	 * @param serviceCost the serviceCost to set
	 */
	public void setServiceCost(ServiceCost serviceCost) {
		this.serviceCost = serviceCost;
	}
	
}
