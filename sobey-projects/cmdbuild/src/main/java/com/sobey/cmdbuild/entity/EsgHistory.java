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
 * EsgHistory generated by hbm2java
 */
@Entity
@Table(name = "esg_history", schema = "public")
public class EsgHistory extends BasicEntity {

	private Integer aclNumber;
	private Date endDate;
	private Esg esg;
	private Boolean isPublic;
	private String remark;
	private Integer tag;
	private Integer tenants;

	public EsgHistory() {
	}

	@Column(name = "acl_number")
	public Integer getAclNumber() {
		return this.aclNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"EndDate\"", nullable = false, length = 29)
	public Date getEndDate() {
		return this.endDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public Esg getEsg() {
		return this.esg;
	}

	@Column(name = "is_public")
	public Boolean getIsPublic() {
		return this.isPublic;
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

	public void setAclNumber(Integer aclNumber) {
		this.aclNumber = aclNumber;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEsg(Esg esg) {
		this.esg = esg;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
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

}
