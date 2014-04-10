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
import com.sobey.cmdbuild.entity.HardDisk;
import com.sobey.cmdbuild.webservice.response.dto.HardDiskDTO;
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
public class HardDiskSoapTest extends BaseFunctionalTestCase {
	private Integer id = 0;

	private String code = "";

	@Test
	public void testAll() {
		testCreateHardDisk();
		testFindHardDisk();
		testGetHardDiskList();
		testGetHardDiskPagination();
		testUpdateHardDisk();
		testDeleteHardDisk();

	}

	// @Test
	// @Ignore
	public void testFindHardDisk() {
		System.out.println(code + ">>>>>>>>>>>>>");

		SearchParams searchParams = new SearchParams();
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("EQ_code", code);
		searchParams.setParamsMap(paramsMap);

		DTOResult<HardDiskDTO> responseParams = cmdbuildSoapService.findHardDiskByParams(searchParams);

		assertEquals(code, responseParams.getDto().getCode());

		id = responseParams.getDto().getId();// 设置id

		DTOResult<HardDiskDTO> response = cmdbuildSoapService.findHardDisk(id);

		assertNotNull(response);

		System.out.println(id + ">>>>>>>>>>>>>");

	}

	// @Test
	// @Ignore
	public void testGetHardDiskList() {

		SearchParams searchParams = new SearchParams();

		DTOListResult<HardDiskDTO> result = cmdbuildSoapService.getHardDiskList(searchParams);

		System.out.println("返回的查询结果数量:" + result.getDtos().size());

		assertEquals("0", result.getCode());

	}

	@Test
	// @Ignore
	public void testCreateHardDisk() {

		HardDisk hardDisk = TestData.randomHardDisk();

		HardDiskDTO hardDiskDTO = BeanMapper.map(hardDisk, HardDiskDTO.class);

		IdResult response = cmdbuildSoapService.createHardDisk(hardDiskDTO);

		assertNotNull(response.getId());

		code = hardDisk.getCode();// 设置code

	}

	// @Test
	// @Ignore
	public void testUpdateHardDisk() {

		DTOResult<HardDiskDTO> response = cmdbuildSoapService.findHardDisk(id);

		HardDiskDTO hardDiskDTO = response.getDto();

		hardDiskDTO.setCode(RandomData.randomName("code"));

		hardDiskDTO.setDescription(RandomData.randomName("update"));

		IdResult result = cmdbuildSoapService.updateHardDisk(id, hardDiskDTO);

		assertEquals("0", result.getCode());

	}

	// @Test
	// @Ignore
	public void testDeleteHardDisk() {

		IdResult response = cmdbuildSoapService.deleteHardDisk(id);

		assertNotNull(response.getId());

	}

	// @Test
	// @Ignore
	public void testGetHardDiskPagination() {

		SearchParams searchParams = new SearchParams();

		PaginationResult<HardDiskDTO> result = cmdbuildSoapService.getHardDiskPagination(searchParams, 1, 10);

		assertNotNull(result.getGetTotalElements());

		System.out.println("返回的查询结果数量:" + result.getGetTotalElements());

	}
}