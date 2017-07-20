package com.novowash.Enums;

public enum STATUS {

	INACTIVE(0), ACTIVE(1) ,DELETE(2),BLOCK(4);
	public int ID;

	STATUS(int id) {
		this.ID = id;
	}
}
