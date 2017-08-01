package com.novowash.model;

import java.util.List;

public class ServiceCategory extends BaseDto {
	private long id;
	private String catName;
	private String catDesc;
	private String imgageUrl;
	private int status;
	private List<Service> services;
	
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
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * @return the catDesc
	 */
	public String getCatDesc() {
		return catDesc;
	}
	/**
	 * @param catDesc the catDesc to set
	 */
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	/**
	 * @return the imgageUrl
	 */
	public String getImgageUrl() {
		return imgageUrl;
	}
	/**
	 * @param imgageUrl the imgageUrl to set
	 */
	public void setImgageUrl(String imgageUrl) {
		this.imgageUrl = imgageUrl;
	}
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
	 * @return the services
	 */
	public List<Service> getServices() {
		return services;
	}
	/**
	 * @param services the services to set
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
}
