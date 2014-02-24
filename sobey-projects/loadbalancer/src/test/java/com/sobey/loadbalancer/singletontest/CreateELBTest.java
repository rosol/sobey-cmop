package com.sobey.loadbalancer.singletontest;

import com.sobey.loadbalancer.data.TestData;
import com.sobey.loadbalancer.service.NitroService;
import com.sobey.loadbalancer.webservice.response.dto.ELBParameter;

/**
 * junit貌似无法启动,故考虑在main中启动CreateELB的测试方法.
 * 
 * @author Administrator
 * 
 */
public class CreateELBTest {

	public static void main(String[] args) {

		ELBParameter parameter = TestData.randomELBParameter();

		NitroService nitroService = new NitroService();
		nitroService.createElb(parameter);
	}

}
