package com.sobey.api.entity;

public class TenantsEntity {

	private String identifier;
	private String name;
	private String email;
	private String phone;
	private String company;

	public TenantsEntity(String identifier, String name, String email, String phone, String company) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.company = company;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
