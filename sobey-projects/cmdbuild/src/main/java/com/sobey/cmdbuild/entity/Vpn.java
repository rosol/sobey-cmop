package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Vpn generated by hbm2java
 */
@Entity
@Table(name = "vpn", schema = "public")
public class Vpn extends BasicEntity {

	private String remark;
	private Integer tag;
	private Integer tenants;
	private Set<VpnHistory> vpnHistories = new HashSet<VpnHistory>(0);
	private String vpnName;
	private String vpnPassword;

	public Vpn() {
	}

	@Column(name = "\"Remark\"", length = 250)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "tag")
	public Integer getTag() {
		return this.tag;
	}

	@Column(name = "tenants")
	public Integer getTenants() {
		return this.tenants;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vpn")
	public Set<VpnHistory> getVpnHistories() {
		return this.vpnHistories;
	}

	@Column(name = "vpn_name", length = 100)
	public String getVpnName() {
		return this.vpnName;
	}

	@Column(name = "vpn_password", length = 100)
	public String getVpnPassword() {
		return this.vpnPassword;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public void setTenants(Integer tenants) {
		this.tenants = tenants;
	}

	public void setVpnHistories(Set<VpnHistory> vpnHistories) {
		this.vpnHistories = vpnHistories;
	}

	public void setVpnName(String vpnName) {
		this.vpnName = vpnName;
	}

	public void setVpnPassword(String vpnPassword) {
		this.vpnPassword = vpnPassword;
	}

}