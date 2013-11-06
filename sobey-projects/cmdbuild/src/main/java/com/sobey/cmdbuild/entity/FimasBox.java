package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FimasBox generated by hbm2java
 */
@Entity
@Table(name = "fimas_box", schema = "public")
public class FimasBox extends BasicEntity {

	private Integer deviceSpec;
	private Integer diskNumber;
	private Integer diskType;
	private Set<FimasBoxHistory> fimasBoxHistories = new HashSet<FimasBoxHistory>(0);
	private String gdzcSn;
	private Integer idc;
	private Integer ipaddress;
	private Integer rack;
	private String remark;
	private Integer site;
	private String sn;

	public FimasBox() {
	}

	@Column(name = "device_spec")
	public Integer getDeviceSpec() {
		return this.deviceSpec;
	}

	@Column(name = "disk_number")
	public Integer getDiskNumber() {
		return this.diskNumber;
	}

	@Column(name = "disk_type")
	public Integer getDiskType() {
		return this.diskType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fimasBox")
	public Set<FimasBoxHistory> getFimasBoxHistories() {
		return this.fimasBoxHistories;
	}

	@Column(name = "\"GdzcSn\"", length = 50)
	public String getGdzcSn() {
		return this.gdzcSn;
	}

	@Column(name = "\"IDC\"")
	public Integer getIdc() {
		return this.idc;
	}

	@Column(name = "ipaddress")
	public Integer getIpaddress() {
		return this.ipaddress;
	}

	@Column(name = "rack")
	public Integer getRack() {
		return this.rack;
	}

	@Column(name = "\"Remark\"", length = 250)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "\"Site\"")
	public Integer getSite() {
		return this.site;
	}

	@Column(name = "\"SN\"", length = 50)
	public String getSn() {
		return this.sn;
	}

	public void setDeviceSpec(Integer deviceSpec) {
		this.deviceSpec = deviceSpec;
	}

	public void setDiskNumber(Integer diskNumber) {
		this.diskNumber = diskNumber;
	}

	public void setDiskType(Integer diskType) {
		this.diskType = diskType;
	}

	public void setFimasBoxHistories(Set<FimasBoxHistory> fimasBoxHistories) {
		this.fimasBoxHistories = fimasBoxHistories;
	}

	public void setGdzcSn(String gdzcSn) {
		this.gdzcSn = gdzcSn;
	}

	public void setIdc(Integer idc) {
		this.idc = idc;
	}

	public void setIpaddress(Integer ipaddress) {
		this.ipaddress = ipaddress;
	}

	public void setRack(Integer rack) {
		this.rack = rack;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSite(Integer site) {
		this.site = site;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
