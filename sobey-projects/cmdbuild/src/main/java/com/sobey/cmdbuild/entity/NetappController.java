package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * NetappController generated by hbm2java
 */
@Entity
@Table(name = "netapp_controller", schema = "public")
public class NetappController extends BasicEntity {

	private String remark;
	private Integer idc;
	private Integer rack;
	private Integer deviceSpec;
	private Integer ipaddress;
	private String site;
	private String sn;
	private String gdzcSn;
	private Set<NetappControllerHistory> netappControllerHistories = new HashSet<NetappControllerHistory>(0);

	public NetappController() {
	}

	@Column(name = "device_spec")
	public Integer getDeviceSpec() {
		return this.deviceSpec;
	}

	@Column(name = "\"GdzcSn\"", length = 50)
	public String getGdzcSn() {
		return this.gdzcSn;
	}

	@Column(name = "\"IDC\"")
	public Integer getIdc() {
		return this.idc;
	}

	@Column(name = "\"IPAddress\"")
	public Integer getIpaddress() {
		return this.ipaddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "netappController")
	public Set<NetappControllerHistory> getNetappControllerHistories() {
		return this.netappControllerHistories;
	}

	@Column(name = "\"Rack\"")
	public Integer getRack() {
		return this.rack;
	}

	@Column(name = "\"Remark\"", length = 250)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "\"Site\"", length = 50)
	public String getSite() {
		return this.site;
	}

	@Column(name = "\"SN\"", length = 50)
	public String getSn() {
		return this.sn;
	}

	public void setDeviceSpec(Integer deviceSpec) {
		this.deviceSpec = deviceSpec;
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

	public void setNetappControllerHistories(Set<NetappControllerHistory> netappControllerHistories) {
		this.netappControllerHistories = netappControllerHistories;
	}

	public void setRack(Integer rack) {
		this.rack = rack;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
