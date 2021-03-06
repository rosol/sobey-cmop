package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.BasicEntity;

/**
 * EipPolicy generated by hbm2java
 */
@Entity
@Table(name = "eip_olicy", schema = "public")
public class EipPolicy extends BasicEntity {

	private Integer eip;
	private Integer eipProtocol;
	private Integer sourcePort;
	private Integer targetPort;
	private Set<EipPolicyHistory> eipPolicyHistories = new HashSet<EipPolicyHistory>(0);

	public EipPolicy() {
	}

	@Column(name = "eip")
	public Integer getEip() {
		return eip;
	}

	public void setEip(Integer eip) {
		this.eip = eip;
	}

	@Column(name = "eip_protocol")
	public Integer getEipProtocol() {
		return eipProtocol;
	}

	public void setEipProtocol(Integer eipProtocol) {
		this.eipProtocol = eipProtocol;
	}

	@Column(name = "source_port")
	public Integer getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}

	@Column(name = "target_port")
	public Integer getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(Integer targetPort) {
		this.targetPort = targetPort;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eipPolicy")
	public Set<EipPolicyHistory> getEipPolicyHistories() {
		return eipPolicyHistories;
	}

	public void setEipPolicyHistories(Set<EipPolicyHistory> eipPolicyHistories) {
		this.eipPolicyHistories = eipPolicyHistories;
	}

}
