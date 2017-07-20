package com.novowash.Enums;

public enum USER_TYPE {

	SUPER_ADMIN(1), FRANCHISEE(2),USER(3);

	public int ID;

	USER_TYPE(int id) {
		this.ID = id;
	}

}
