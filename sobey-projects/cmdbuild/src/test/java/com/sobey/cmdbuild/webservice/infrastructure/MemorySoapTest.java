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
import com.sobey.cmdbuild.entity.Memory;
import com.sobey.cmdbuild.webservice.response.dto.MemoryDTO;
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
public class MemorySoapTest extends BaseFunctionalTestCase {
	private Integer id = 0;

	private String code = "";

	@Test
	public void testAll() {
		testCreateMemory();
		testFindMemory();
		testGetMemoryList();
		testGetMemoryPagination();
		testUpdateMemory();
		testDeleteMemory();

	}

	// @Test
	// @Ignore
	public void testFindMemory() {
		System.out.println(code + ">>>>>>>>>>>>>");

		SearchParams searchParams = new SearchParams();
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("EQ_code", code);
		searchParams.setParamsMap(paramsMap);

		DTOResult<MemoryDTO> responseParams = cmdbuildSoapService.findMemoryByParams(searchParams);

		assertEquals(code, responseParams.getDto().getCode());

		id = responseParams.getDto().getId();// 设置id

		DTOResult<MemoryDTO> response = cmdbuildSoapService.findMemory(id);

		assertNotNull(response);

		System.out.println(id + ">>>>>>>>>>>>>");

	}

	// @Test
	// @Ignore
	public void testGetMemoryList() {

		SearchParams searchParams = new SearchParams();

		DTOListResult<MemoryDTO> result = cmdbuildSoapService.getMemoryList(searchParams);

		System.out.println("返回的查询结果数量:" + result.getDtos().size());

		assertEquals("0", result.getCode());

	}

	// @Test
	// @Ignore
	public void testCreateMemory() {

		Memory memory = TestData.randomMemory();

		MemoryDTO memoryDTO = BeanMapper.map(memory, MemoryDTO.class);

		IdResult response = cmdbuildSoapService.createMemory(memoryDTO);

		assertNotNull(response.getId());

		code = memory.getCode();// 设置code

	}

	// @Test
	// @Ignore
	public void testUpdateMemory() {

		DTOResult<MemoryDTO> response = cmdbuildSoapService.findMemory(id);

		MemoryDTO memoryDTO = response.getDto();

		memoryDTO.setCode(RandomData.randomName("code"));

		memoryDTO.setDescription(RandomData.randomName("update"));

		IdResult result = cmdbuildSoapService.updateMemory(id, memoryDTO);

		assertEquals("0", result.getCode());

	}

	// @Test
	// @Ignore
	public void testDeleteMemory() {

		IdResult response = cmdbuildSoapService.deleteMemory(id);

		assertNotNull(response.getId());

	}

	// @Test
	// @Ignore
	public void testGetMemoryPagination() {

		SearchParams searchParams = new SearchParams();

		PaginationResult<MemoryDTO> result = cmdbuildSoapService.getMemoryPagination(searchParams, 1, 10);

		assertNotNull(result.getGetTotalElements());

		System.out.println("返回的查询结果数量:" + result.getGetTotalElements());

	}
}