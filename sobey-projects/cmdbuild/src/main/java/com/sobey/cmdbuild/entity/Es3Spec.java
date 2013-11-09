package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Es3Spec generated by hbm2java
 */
@Entity
@Table(name = "es3_spec", schema = "public")
public class Es3Spec extends BasicEntity {

	private Set<Es3SpecHistory> es3SpecHistories = new HashSet<Es3SpecHistory>(0);
	private Double price;
	private String remark;
	private Integer maxSpace;
	private Integer iops;
	private Integer brand;

	public Es3Spec() {
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "es3Spec")
	public Set<Es3SpecHistory> getEs3SpecHistories() {
		return this.es3SpecHistories;
	}

	@Column(name = "\"Brand\"")
	public Integer getBrand() {
		return brand;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
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

	public void setEs3SpecHistories(Set<Es3SpecHistory> es3SpecHistories) {
		this.es3SpecHistories = es3SpecHistories;
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
