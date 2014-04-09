package com.sobey.cmdbuild.webservice.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.sobey.cmdbuild.BaseFunctionalTestCase;
import com.sobey.cmdbuild.data.TestData;
import com.sobey.cmdbuild.entity.Vlan;
import com.sobey.cmdbuild.webservice.response.dto.VlanDTO;
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
public class VlanSoapTest extends BaseFunctionalTestCase {
	private Integer id = 0;

	private String code = "";

	@Test
	public void testAll() {
		testCreateVlan();
		testFindVlan();
		testGetVlanList();
		testGetVlanPagination();
		testUpdateVlan();
		testDeleteVlan();

	}

	// @Test
	// @Ignore
	public void testFindVlan() {
		System.out.println(code + ">>>>>>>>>>>>>");

		SearchParams searchParams = new SearchParams();
		searchParams.getParamsMap().put("EQ_code", code);

		DTOResult<VlanDTO> responseParams = cmdbuildSoapService.findVlanByParams(searchParams);

		assertEquals(code, responseParams.getDto().getCode());

		id = responseParams.getDto().getId();// 设置id

		DTOResult<VlanDTO> response = cmdbuildSoapService.findVlan(id);

		assertNotNull(response);

		System.out.println(id + ">>>>>>>>>>>>>");

	}

	// @Test
	// @Ignore
	public void testGetVlanList() {

		SearchParams searchParams = new SearchParams();
		searchParams.getParamsMap().put("EQ_code", code);

		DTOListResult<VlanDTO> result = cmdbuildSoapService.getVlanList(searchParams);

		System.out.println("返回的查询结果数量:" + result.getDtos().size());

		assertEquals("0", result.getCode());

	}

	@Test
	// @Ignore
	public void testCreateVlan() {

		Vlan vlan = TestData.randomVlan();

		VlanDTO vlanDTO = BeanMapper.map(vlan, VlanDTO.class);

		IdResult response = cmdbuildSoapService.createVlan(vlanDTO);

		assertNotNull(response.getId());

		code = vlan.getCode();// 设置code

	}

	// @Test
	// @Ignore
	public void testUpdateVlan() {

		DTOResult<VlanDTO> response = cmdbuildSoapService.findVlan(id);

		VlanDTO vlanDTO = response.getDto();

		vlanDTO.setCode(RandomData.randomName("code"));

		vlanDTO.setDescription(RandomData.randomName("update"));

		IdResult result = cmdbuildSoapService.updateVlan(id, vlanDTO);

		assertEquals("0", result.getCode());

	}

	// @Test
	// @Ignore
	public void testDeleteVlan() {

		IdResult response = cmdbuildSoapService.deleteVlan(id);

		assertNotNull(response.getId());

	}

	// @Test
	// @Ignore
	public void testGetVlanPagination() {

		SearchParams searchParams = new SearchParams();

		PaginationResult<VlanDTO> result = cmdbuildSoapService.getVlanPagination(searchParams, 1, 10);

		assertNotNull(result.getGetTotalElements());

		System.out.println("返回的查询结果数量:" + result.getGetTotalElements());

	}

	/**
	 * 批量添加Vlan
	 */
	@Test
	// @Ignore
	public void testInsertVlan() {

		List<VlanDTO> list = new ArrayList<VlanDTO>();

		for (int i = 0; i < 10; i++) {

			Vlan vlan = TestData.randomVlan();

			VlanDTO vlanDTO = BeanMapper.map(vlan, VlanDTO.class);

			list.add(vlanDTO);

		}

		IdResult results = cmdbuildSoapService.insertVlan(list);
		System.err.println(results.getMessage());

	}

}