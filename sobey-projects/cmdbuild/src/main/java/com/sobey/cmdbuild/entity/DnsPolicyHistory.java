package com.sobey.cmdbuild.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EsgPolicyHistory generated by hbm2java
 */
@Entity
@Table(name = "dns_policy_history", schema = "public")
public class DnsPolicyHistory extends BasicEntity {

	private DnsPolicy dnsPolicy;
	private Date endDate;
	private Integer dns;
	private Integer port;
	private Integer esgProtocol;

	public DnsPolicyHistory() {
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"EndDate\"", nullable = false, length = 29)
	public Date getEndDate() {
		return this.endDate;
	}

	@Column(name = "dns")
	public Integer getDns() {
		return dns;
	}

	public void setDns(Integer dns) {
		this.dns = dns;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public DnsPolicy getDnsPolicy() {
		return dnsPolicy;
	}

	public void setDnsPolicy(DnsPolicy dnsPolicy) {
		this.dnsPolicy = dnsPolicy;
	}

	@Column(name = "esg_protocol")
	public Integer getEsgProtocol() {
		return this.esgProtocol;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEsgProtocol(Integer esgProtocol) {
		this.esgProtocol = esgProtocol;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
