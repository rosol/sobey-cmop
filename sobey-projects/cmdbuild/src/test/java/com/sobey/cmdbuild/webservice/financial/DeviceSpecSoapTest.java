package com.sobey.cmdbuild.webservice.financial;

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
import com.sobey.cmdbuild.entity.DeviceSpec;
import com.sobey.cmdbuild.webservice.response.dto.DeviceSpecDTO;
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
public class DeviceSpecSoapTest extends BaseFunctionalTestCase {
	private Integer id = 0;

	private String code = "";

	@Test
	public void testAll() {
		testCreateDeviceSpec();
		testFindDeviceSpec();
		testGetDeviceSpecList();
		testGetDeviceSpecPagination();
		testUpdateDeviceSpec();
		// testDeleteDeviceSpec();

	}

	// @Test
	// @Ignore
	public void testFindDeviceSpec() {
		System.out.println(code + ">>>>>>>>>>>>>");

		SearchParams searchParams = new SearchParams();
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("EQ_code", code);
		searchParams.setParamsMap(paramsMap);

		DTOResult<DeviceSpecDTO> responseParams = cmdbuildSoapService.findDeviceSpecByParams(searchParams);

		assertEquals(code, responseParams.getDto().getCode());

		id = responseParams.getDto().getId();// 设置id

		DTOResult<DeviceSpecDTO> response = cmdbuildSoapService.findDeviceSpec(id);

		assertNotNull(response);

		System.out.println(id + ">>>>>>>>>>>>>");

	}

	// @Test
	// @Ignore
	public void testGetDeviceSpecList() {

		SearchParams searchParams = new SearchParams();

		DTOListResult<DeviceSpecDTO> result = cmdbuildSoapService.getDeviceSpecList(searchParams);

		System.out.println("返回的查询结果数量:" + result.getDtos().size());

		assertEquals("0", result.getCode());

	}

	@Test
	// @Ignore
	public void testCreateDeviceSpec() {

		DeviceSpec deviceSpec = TestData.randomDeviceSpec();
		// deviceSpec.setCode("code7473");

		DeviceSpecDTO deviceSpecDTO = BeanMapper.map(deviceSpec, DeviceSpecDTO.class);

		IdResult response = cmdbuildSoapService.createDeviceSpec(deviceSpecDTO);

		assertNotNull(response.getId());

		code = deviceSpec.getCode();// 设置code

	}

	// @Test
	// @Ignore
	public void testUpdateDeviceSpec() {

		DTOResult<DeviceSpecDTO> response = cmdbuildSoapService.findDeviceSpec(id);

		DeviceSpecDTO deviceSpecDTO = response.getDto();

		deviceSpecDTO.setCode(RandomData.randomName("code"));

		deviceSpecDTO.setDescription(RandomData.randomName("desc"));

		deviceSpecDTO.setDescription(RandomData.randomName("description"));

		IdResult result = cmdbuildSoapService.updateDeviceSpec(id, deviceSpecDTO);

		assertEquals("0", result.getCode());

	} // @Test

	// @Ignore
	public void testDeleteDeviceSpec() {

		IdResult response = cmdbuildSoapService.deleteDeviceSpec(id);

		assertNotNull(response.getId());

	}

	@Test
	// @Ignore
	public void testGetDeviceSpecPagination() {

		SearchParams searchParams = new SearchParams();

		PaginationResult<DeviceSpecDTO> result = cmdbuildSoapService.getDeviceSpecPagination(searchParams, 1, 10);

		assertNotNull(result.getGetTotalElements());

		System.out.println("返回的查询结果数量:" + result.getGetTotalElements());

	}
}