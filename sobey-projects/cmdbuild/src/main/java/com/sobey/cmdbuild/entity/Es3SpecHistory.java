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
 * Es3SpecHistory generated by hbm2java
 */
@Entity
@Table(name = "es3_spec_history", schema = "public")
public class Es3SpecHistory extends BasicEntity {

	private Es3Spec es3Spec;
	private Date endDate;
	private String remark;
	private Integer maxSpace;
	private Integer iops;
	private Integer brand;
	private Double price;

	public Es3SpecHistory() {
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"EndDate\"", nullable = false, length = 29)
	public Date getEndDate() {
		return this.endDate;
	}

	@Column(name = "\"Brand\"")
	public Integer getBrand() {
		return brand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public Es3Spec getEs3Spec() {
		return this.es3Spec;
	}

	@Column(name = "\"Price\"", precision = 17, scale = 17)
	public Double getPrice() {
		return this.price;
	}

	@Column(name = "\"Remark\"", length = 250)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "max_space")
	public Integer getMaxSpace() {
		return maxSpace;
	}

	@Column(name = "iops")
	public Integer getIops() {
		return iops;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEs3Spec(Es3Spec es3Spec) {
		this.es3Spec = es3Spec;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setMaxSpace(Integer maxSpace) {
		this.maxSpace = maxSpace;
	}

	public void setIops(Integer iops) {
		this.iops = iops;
	}
}
