package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ipaddress generated by hbm2java
 */
@Entity
@Table(name = "ipaddress", schema = "public")
public class Ipaddress extends BasicEntity {

	private Integer vlan;
	private Integer isp;
	private Integer ipaddressPool;
	private Integer ipaddressStatus;
	private String netMask;
	private String gateway;
	private Set<IpaddressHistory> ipaddressHistories = new HashSet<IpaddressHistory>(0);

	public Ipaddress() {
	}

	@Column(name = "gateway", length = 50)
	public String getGateway() {
		return this.gateway;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ipaddress")
	public Set<IpaddressHistory> getIpaddressHistories() {
		return this.ipaddressHistories;
	}

	@Column(name = "ipaddress_pool")
	public Integer getIpaddressPool() {
		return this.ipaddressPool;
	}

	@Column(name = "ipaddress_status")
	public Integer getIpaddressStatus() {
		return this.ipaddressStatus;
	}

	@Column(name = "\"ISP\"")
	public Integer getIsp() {
		return this.isp;
	}

	@Column(name = "net_mask", length = 50)
	public String getNetMask() {
		return this.netMask;
	}

	@Column(name = "vlan")
	public Integer getVlan() {
		return this.vlan;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public void setIpaddressHistories(Set<IpaddressHistory> ipaddressHistories) {
		this.ipaddressHistories = ipaddressHistories;
	}

	public void setIpaddressPool(Integer ipaddressPool) {
		this.ipaddressPool = ipaddressPool;
	}

	public void setIpaddressStatus(Integer ipaddressStatus) {
		this.ipaddressStatus = ipaddressStatus;
	}

	public void setIsp(Integer isp) {
		this.isp = isp;
	}

	public void setNetMask(String netMask) {
		this.netMask = netMask;
	}

	public void setVlan(Integer vlan) {
		this.vlan = vlan;
	}

}
