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

	private String resgroup;
	private String vendor;
	private String model;
	private String memorySize;
	private String cpuNumber;
	private String cpuHz;
	private Set<ServerHistory> serverHistories = new HashSet<ServerHistory>(0);

	public Server() {
	}

	@Column(name = "resgroup", length = 100)
	public String getResgroup() {
		return resgroup;
	}

	public void setResgroup(String resgroup) {
		this.resgroup = resgroup;
	}

	@Column(name = "vendor", length = 100)
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Column(name = "model", length = 100)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "memory_size", length = 100)
	public String getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}

	@Column(name = "cpu_number", length = 100)
	public String getCpuNumber() {
		return cpuNumber;
	}

	public void setCpuNumber(String cpuNumber) {
		this.cpuNumber = cpuNumber;
	}

	@Column(name = "cpu_hz", length = 100)
	public String getCpuHz() {
		return cpuHz;
	}

	public void setCpuHz(String cpuHz) {
		this.cpuHz = cpuHz;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "server")
	public Set<ServerHistory> getServerHistories() {
		return this.serverHistories;
	}

	public void setServerHistories(Set<ServerHistory> serverHistories) {
		this.serverHistories = serverHistories;
	}

}
