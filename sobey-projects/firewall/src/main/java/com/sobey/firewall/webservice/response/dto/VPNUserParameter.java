package com.sobey.firewall.webservice.response.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.sobey.firewall.constans.WsConstants;

/**
 * 防火墙上,执行脚本所需参数的VPNUserParameter对象:
 * 
 * <pre>
 * firewallPolicyId	防火墙中的策略ID,从2000起递增.
 * VlanId		Vlan编号(code)
 * ipaddress	单个IP集合
 * segments		网段集合
 * netMask		子网掩码
 * vpnUser		VPN用户名
 * vpnPassword	VPN密码
 * </pre>
 * 
 * @author Administrator
 * 
 */
@XmlRootElement(name = "VPNUserParameter")
@XmlType(name = "VPNUserParameter", namespace = WsConstants.NS)
public class VPNUserParameter {

	/**
	 * 防火墙中的策略ID,从2000起递增.
	 */
	private Integer firewallPolicyId;

	/**
	 * Vlan编号(code)
	 */
	private Integer VlanId;

	/**
	 * IP
	 */
	private ArrayList<String> ipaddress;

	/**
	 * 网段
	 */
	private ArrayList<String> segments;

	/**
	 * 子网掩码
	 */
	private String netMask;

	/**
	 * VPN用户名
	 */
	private String vpnUser;

	/**
	 * VPN密码
	 */
	private String vpnPassword;

	public Integer getFirewallPolicyId() {
		return firewallPolicyId;
	}

	public void setFirewallPolicyId(Integer firewallPolicyId) {
		this.firewallPolicyId = firewallPolicyId;
	}

	public Integer getVlanId() {
		return VlanId;
	}

	public void setVlanId(Integer vlanId) {
		VlanId = vlanId;
	}

	public ArrayList<String> getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(ArrayList<String> ipaddress) {
		this.ipaddress = ipaddress;
	}

	public ArrayList<String> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<String> segments) {
		this.segments = segments;
	}

	public String getNetMask() {
		return netMask;
	}

	public void setNetMask(String netMask) {
		this.netMask = netMask;
	}

	public String getVpnUser() {
		return vpnUser;
	}

	public void setVpnUser(String vpnUser) {
		this.vpnUser = vpnUser;
	}

	public String getVpnPassword() {
		return vpnPassword;
	}

	public void setVpnPassword(String vpnPassword) {
		this.vpnPassword = vpnPassword;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
