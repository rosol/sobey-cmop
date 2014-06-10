package com.sobey.nagios.webservice.response.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.sobey.nagios.constans.WsConstants;
import com.sobey.nagios.entity.NagiosSwapUsage;

@XmlRootElement(name = "NagiosSwapUsageDTO")
@XmlType(name = "NagiosSwapUsageDTO", namespace = WsConstants.NS)
public class NagiosSwapUsageDTO {

	private ArrayList<NagiosSwapUsage> nagiosSwapUsages;

	public ArrayList<NagiosSwapUsage> getNagiosSwapUsages() {
		return nagiosSwapUsages;
	}

	public void setNagiosSwapUsages(ArrayList<NagiosSwapUsage> nagiosSwapUsages) {
		this.nagiosSwapUsages = nagiosSwapUsages;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
