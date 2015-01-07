package com.sobey.cmdbuild.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.BasicEntity;

/**
 * ConfigSystemInterfaceHistory generated by hbm2java
 */
@Entity
@Table(name = "config_system_interface_history", schema = "public")
public class ConfigSystemInterfaceHistory extends BasicEntity {

	private ConfigSystemInterface configSystemInterface;
	/**
	 * 所属EIP
	 */
	private Integer eip;

	/**
	 * 防火墙策略ID,唯一递增
	 */
	private Integer policyId;

	/**
	 * PortId
	 * 
	 * 接口名的组成格式为 "port"+"id" ,其中id 为 1-10 中一个.
	 */
	private Integer portId;

	/**
	 * 所属Tenants
	 */
	private Integer tenants;

	public ConfigSystemInterfaceHistory() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public ConfigSystemInterface getConfigSystemInterface() {
		return configSystemInterface;
	}

	@Column(name = "eip")
	public Integer getEip() {
		return eip;
	}

	@Column(name = "policy_id")
	public Integer getPolicyId() {
		return policyId;
	}

	@Column(name = "port_id")
	public Integer getPortId() {
		return portId;
	}

	@Column(name = "tenants")
	public Integer getTenants() {
		return tenants;
	}

	public void setConfigSystemInterface(ConfigSystemInterface configSystemInterface) {
		this.configSystemInterface = configSystemInterface;
	}

	public void setEip(Integer eip) {
		this.eip = eip;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
	}

	public void setTenants(Integer tenants) {
		this.tenants = tenants;
	}

}