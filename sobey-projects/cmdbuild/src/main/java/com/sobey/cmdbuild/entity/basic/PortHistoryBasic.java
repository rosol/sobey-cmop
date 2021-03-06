package com.sobey.cmdbuild.entity.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PortHistory 模块的基本Entity
 * 
 * <p>
 * SwitchPort ServerPort NicPort FirewallPort LoadBalancerPort StoragePort
 * </p>
 * 
 * @author Administrator
 *
 */
@MappedSuperclass
public class PortHistoryBasic extends BasicEntity {

	protected Integer idc;
	protected Integer connectedTo;
	protected Integer ipaddress;
	protected String site;
	protected String macAddress;
	protected String remark;
	protected Date endDate;

	@Column(name = "idc")
	public Integer getIdc() {
		return idc;
	}

	public void setIdc(Integer idc) {
		this.idc = idc;
	}

	@Column(name = "connected_to")
	public Integer getConnectedTo() {
		return connectedTo;
	}

	public void setConnectedTo(Integer connectedTo) {
		this.connectedTo = connectedTo;
	}

	@Column(name = "ipaddress")
	public Integer getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(Integer ipaddress) {
		this.ipaddress = ipaddress;
	}

	@Column(name = "site", length = 100)
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "mac_address", length = 100)
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"EndDate\"", nullable = false, length = 29)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
