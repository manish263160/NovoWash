package com.novowash.Enums;

import java.io.Serializable;

public enum UserRoleType implements Serializable{
	SUPERADMIN("SUPERADMIN"),
	ADMIN("ADMIN"),
	USER("USER"),
	FRANCHISEE("RESTORENT");
	
	
	String userProfileType;
	
	private UserRoleType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
