package com.sobey.cmdbuild.webservice.response.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.sobey.cmdbuild.constants.WsConstants;

@XmlRootElement(name = "NicDTO")
@XmlType(name = "NicDTO", namespace = WsConstants.NS)
public class NicDTO {

	private Integer id;
	private String code;
	private String description;
	private Date beginDate;
	private Integer idc;
	private IdcDTO idcDTO;
	private Integer server;
	private ServerDTO serverDTO;
	private Integer fimas;
	private FimasDTO fimasDTO;
	private Integer brand;
	private Integer nicRate;
	private String brandText;
	private String nicRateText;
	private Integer portNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Integer getIdc() {
		return idc;
	}

	public void setIdc(Integer idc) {
		this.idc = idc;
	}

	public IdcDTO getIdcDTO() {
		return idcDTO;
	}

	public void setIdcDTO(IdcDTO idcDTO) {
		this.idcDTO = idcDTO;
	}

	public Integer getServer() {
		return server;
	}

	public void setServer(Integer server) {
		this.server = server;
	}

	public ServerDTO getServerDTO() {
		return serverDTO;
	}

	public void setServerDTO(ServerDTO serverDTO) {
		this.serverDTO = serverDTO;
	}

	public Integer getFimas() {
		return fimas;
	}

	public void setFimas(Integer fimas) {
		this.fimas = fimas;
	}

	public FimasDTO getFimasDTO() {
		return fimasDTO;
	}

	public void setFimasDTO(FimasDTO fimasDTO) {
		this.fimasDTO = fimasDTO;
	}

	public Integer getBrand() {
		return brand;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public Integer getNicRate() {
		return nicRate;
	}

	public void setNicRate(Integer nicRate) {
		this.nicRate = nicRate;
	}

	public String getBrandText() {
		return brandText;
	}

	public void setBrandText(String brandText) {
		this.brandText = brandText;
	}

	public String getNicRateText() {
		return nicRateText;
	}

	public void setNicRateText(String nicRateText) {
		this.nicRateText = nicRateText;
	}

	public Integer getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(Integer portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}