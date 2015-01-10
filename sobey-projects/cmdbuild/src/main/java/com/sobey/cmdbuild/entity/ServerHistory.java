package com.sobey.cmdbuild.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.DeviceHistoryBasic;

/**
 * ServerHistory generated by hbm2java
 */
@Entity
@Table(name = "server_history", schema = "public")
public class ServerHistory extends DeviceHistoryBasic {

	private String cpuHz;
	private String cpuNumber;
	private String hostgroup;
	private String memorySize;
	private String model;
	private String resgroup;
	private Server server;
	private String vendor;

	public ServerHistory() {
	}

	@Column(name = "cpu_hz", length = 100)
	public String getCpuHz() {
		return cpuHz;
	}

	@Column(name = "cpu_number", length = 100)
	public String getCpuNumber() {
		return cpuNumber;
	}

	@Column(name = "hostgroup", length = 100)
	public String getHostgroup() {
		return hostgroup;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public Server getServer() {
		return server;
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

	public void setHostgroup(String hostgroup) {
		this.hostgroup = hostgroup;
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

	public void setServer(Server server) {
		this.server = server;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
