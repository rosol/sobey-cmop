package com.sobey.cmdbuild.webservice.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.sobey.cmdbuild.BaseFunctionalTestCase;
import com.sobey.cmdbuild.data.TestData;
import com.sobey.cmdbuild.entity.Firewall;
import com.sobey.cmdbuild.webservice.response.dto.FirewallDTO;
import com.sobey.cmdbuild.webservice.response.result.DTOListResult;
import com.sobey.cmdbuild.webservice.response.result.DTOResult;
import com.sobey.cmdbuild.webservice.response.result.IdResult;
import com.sobey.cmdbuild.webservice.response.result.PaginationResult;
import com.sobey.cmdbuild.webservice.response.result.SearchParams;
import com.sobey.core.mapper.BeanMapper;
import com.sobey.test.data.RandomData;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-soap-client.xml" })
public class FirewallSoapTest extends BaseFunctionalTestCase {
	private Integer id = 0;

	private String code = "";

	@Test
	public void testAll() {
		testCreateFirewall();
		testFindFirewall();
		testGetFirewallList();
		testGetFirewallPagination();
		testUpdateFirewall();
		testDeleteFirewall();

	}

	// @Test
	// @Ignore
	public void testFindFirewall() {
		System.out.println(code + ">>>>>>>>>>>>>");

		SearchParams searchParams = new SearchParams();
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("EQ_code", code);
		searchParams.setParamsMap(paramsMap);

		DTOResult<FirewallDTO> responseParams = cmdbuildSoapService.findFirewallByParams(searchParams);

		assertEquals(code, responseParams.getDto().getCode());

		id = responseParams.getDto().getId();// 设置id

		DTOResult<FirewallDTO> response = cmdbuildSoapService.findFirewall(id);

		assertNotNull(response);

		System.out.println(id + ">>>>>>>>>>>>>");

	}

	// @Test
	// @Ignore
	public void testGetFirewallList() {

		SearchParams searchParams = new SearchParams();

		DTOListResult<FirewallDTO> result = cmdbuildSoapService.getFirewallList(searchParams);

		System.out.println("返回的查询结果数量:" + result.getDtos().size());

		assertEquals("0", result.getCode());

	}

	// @Test
	// @Ignore
	public void testCreateFirewall() {

		Firewall firewall = TestData.randomFirewall();

		FirewallDTO firewallDTO = BeanMapper.map(firewall, FirewallDTO.class);

		IdResult response = cmdbuildSoapService.createFirewall(firewallDTO);

		assertNotNull(response.getId());

		code = firewall.getCode();// 设置code

	}

	// @Test
	// @Ignore
	public void testUpdateFirewall() {

		DTOResult<FirewallDTO> response = cmdbuildSoapService.findFirewall(id);

		FirewallDTO firewallDTO = response.getDto();

		firewallDTO.setCode(RandomData.randomName("code"));

		firewallDTO.setDescription(RandomData.randomName("update"));

		IdResult result = cmdbuildSoapService.updateFirewall(id, firewallDTO);

		assertEquals("0", result.getCode());

	}

	// @Test
	// @Ignore
	public void testDeleteFirewall() {

		IdResult response = cmdbuildSoapService.deleteFirewall(id);

		assertNotNull(response.getId());

	}

	// @Test
	// @Ignore
	public void testGetFirewallPagination() {

		SearchParams searchParams = new SearchParams();

		PaginationResult<FirewallDTO> result = cmdbuildSoapService.getFirewallPagination(searchParams, 1, 10);

		assertNotNull(result.getGetTotalElements());

		System.out.println("返回的查询结果数量:" + result.getGetTotalElements());

	}
}