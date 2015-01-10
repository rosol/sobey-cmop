package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.DeviceBasic;

/**
 * Server generated by hbm2java
 */
@Entity
@Table(name = "server", schema = "public")
public class Server extends DeviceBasic {

	private String cpuHz;
	private String cpuNumber;
	private String hsotgroup;
	private String memorySize;
	private String model;
	private String resgroup;
	private Set<ServerHistory> serverHistories = new HashSet<ServerHistory>(0);
	private String vendor;

	public Server() {
	}

	@Column(name = "cpu_hz", length = 100)
	public String getCpuHz() {
		return cpuHz;
	}

	@Column(name = "cpu_number", length = 100)
	public String getCpuNumber() {
		return cpuNumber;
	}

	@Column(name = "hsotgroup", length = 100)
	public String getHsotgroup() {
		return hsotgroup;
	}

	@Column(name = "memory_size", length = 100)
	public String getMemorySize() {
		return memorySize;
	}

	@Column(name = "model", length = 100)
	public String getModel() {
		return model;
	}

	@Column(name = "resgroup", length = 100)
	public String getResgroup() {
		return resgroup;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
	public Set<ServerHistory> getServerHistories() {
		return this.serverHistories;
	}

	@Column(name = "vendor", length = 100)
	public String getVendor() {
		return vendor;
	}

	public void setCpuHz(String cpuHz) {
		this.cpuHz = cpuHz;
	}

	public void setCpuNumber(String cpuNumber) {
		this.cpuNumber = cpuNumber;
	}

	public void setHsotgroup(String hsotgroup) {
		this.hsotgroup = hsotgroup;
	}

	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setResgroup(String resgroup) {
		this.resgroup = resgroup;
	}

	public void setServerHistories(Set<ServerHistory> serverHistories) {
		this.serverHistories = serverHistories;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
