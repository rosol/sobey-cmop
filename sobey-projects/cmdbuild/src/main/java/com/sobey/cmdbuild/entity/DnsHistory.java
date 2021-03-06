package com.sobey.cmdbuild.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.ServiceHistoryBasic;

/**
 * DnsHistory generated by hbm2java
 */
@Entity
@Table(name = "dns_history", schema = "public")
public class DnsHistory extends ServiceHistoryBasic {

	private Dns dns;
	private Integer domainType;
	private String domainName;
	private String cnameDomain;

	public DnsHistory() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public Dns getDns() {
		return dns;
	}

	public void setDns(Dns dns) {
		this.dns = dns;
	}

	@Column(name = "domain_type")
	public Integer getDomainType() {
		return domainType;
	}

	public void setDomainType(Integer domainType) {
		this.domainType = domainType;
	}

	@Column(name = "\"domainName\"", length = 100)
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Column(name = "\"cnameDomain\"", length = 100)
	public String getCnameDomain() {
		return cnameDomain;
	}

	public void setCnameDomain(String cnameDomain) {
		this.cnameDomain = cnameDomain;
	}

}
