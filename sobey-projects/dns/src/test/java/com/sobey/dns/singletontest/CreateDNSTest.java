package com.sobey.dns.singletontest;

import com.sobey.dns.data.TestData;
import com.sobey.dns.service.NitroService;
import com.sobey.dns.webservice.response.dto.DNSParameter;

/**
 * junit貌似无法启动,故考虑在main中启动CreateDNS的测试方法.
 * 
 * @author Administrator
 * 
 */
public class CreateDNSTest extends PropertiesAbstract {

	public static void main(String[] args) {

		DNSParameter parameter = TestData.randomDNSParameter();

		NitroService nitroService = new NitroService();
		nitroService.createDns(parameter);

	}

}
