package com.sobey.nagios.webservice.response.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.sobey.nagios.constans.WsConstants;
import com.sobey.nagios.entity.NagiosUpTime;

@XmlRootElement(name = "NagiosUpTimeDTO")
@XmlType(name = "NagiosUpTimeDTO", namespace = WsConstants.NS)
public class NagiosUpTimeDTO {

	private ArrayList<NagiosUpTime> nagiosUpTimes;

	public ArrayList<NagiosUpTime> getNagiosUpTimes() {
		return nagiosUpTimes;
	}

	public void setNagiosUpTimes(ArrayList<NagiosUpTime> nagiosUpTimes) {
		this.nagiosUpTimes = nagiosUpTimes;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
