package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FimasPort generated by hbm2java
 */
@Entity
@Table(name = "fimas_port", schema = "public")
public class FimasPort extends BasicEntity {

	private Integer connectedTo;
	private Integer fimas;
	private Set<FimasPortHistory> fimasPortHistories = new HashSet<FimasPortHistory>(0);
	private Integer ipaddress;
	private String macAddress;
	private String remark;
	private String site;

	public FimasPort() {
	}

	@Column(name = "connected_to")
	public Integer getConnectedTo() {
		return this.connectedTo;
	}

	@Column(name = "fimas")
	public Integer getFimas() {
		return this.fimas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fimasPort")
	public Set<FimasPortHistory> getFimasPortHistories() {
		return this.fimasPortHistories;
	}

	@Column(name = "ipaddress")
	public Integer getIpaddress() {
		return this.ipaddress;
	}

	@Column(name = "mac_address", length = 50)
	public String getMacAddress() {
		return this.macAddress;
	}

	@Column(name = "\"Remark\"", length = 50)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "\"Site\"", length = 50)
	public String getSite() {
		return this.site;
	}

	public void setConnectedTo(Integer connectedTo) {
		this.connectedTo = connectedTo;
	}

	public void setFimas(Integer fimas) {
		this.fimas = fimas;
	}

	public void setFimasPortHistories(Set<FimasPortHistory> fimasPortHistories) {
		this.fimasPortHistories = fimasPortHistories;
	}

	public void setIpaddress(Integer ipaddress) {
		this.ipaddress = ipaddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
