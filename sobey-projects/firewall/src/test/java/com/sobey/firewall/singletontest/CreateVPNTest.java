package com.sobey.firewall.singletontest;

import com.sobey.firewall.data.TestData;
import com.sobey.firewall.script.GenerateScript;
import com.sobey.firewall.webservice.response.dto.VPNParameter;

/**
 * junit貌似无法启动,故考虑在main中启动CreateVPN的测试方法.
 * 
 * @author Administrator
 * 
 */
public class CreateVPNTest extends PropertiesAbstract {

	public static void main(String[] args) {

		VPNParameter parameter = TestData.randomVPNParameter();
		VPNParameter parameter2 = TestData.randomVPNParameterSingletonIP();

		// 网关的控制
		String command = GenerateScript.generateCreateVPNScript(parameter);
		System.out.println(command);

		System.err.println("------------------------------");

		// 单IP的控制
		String command2 = GenerateScript.generateCreateVPNScript(parameter2);
		System.out.println(command2);

		// TelnetUtil.execCommand(FIREWALL_IP, FIREWALL_USERNAME, FIREWALL_PASSWORD, command);

	}

}