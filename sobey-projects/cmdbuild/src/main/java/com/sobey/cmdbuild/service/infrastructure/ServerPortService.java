package com.sobey.cmdbuild.service.infrastructure;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sobey.cmdbuild.constants.CMDBuildConstants;
import com.sobey.cmdbuild.entity.ServerPort;
import com.sobey.cmdbuild.repository.ServerPortDao;
import com.sobey.cmdbuild.service.BasicSevcie;
import com.sobey.cmdbuild.webservice.response.dto.ServerPortDTO;
import com.sobey.cmdbuild.webservice.response.result.PaginationResult;
import com.sobey.core.mapper.BeanMapper;
import com.sobey.core.persistence.DynamicSpecifications;
import com.sobey.core.persistence.SearchFilter;

/**
 * ServerPort的service类.
 */
@Service
@Transactional
public class ServerPortService extends BasicSevcie {
	@Autowired
	private ServerPortDao serverPortDao;

	/**
	 * 根据ID获得对象
	 * 
	 * @param id
	 * @return ServerPort
	 */
	public ServerPort findServerPort(Integer id) {
		return serverPortDao.findOne(id);
	}

	/**
	 * 新增、保存对象
	 * 
	 * @param serverPort
	 * @return ServerPort
	 */
	public ServerPort saveOrUpdate(ServerPort serverPort) {
		return serverPortDao.save(serverPort);
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public void deleteServerPort(Integer id) {
		serverPortDao.delete(id);
	}

	/**
	 * Spring-data-jpa自带的分页查询
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<ServerPort>
	 */
	private Page<ServerPort> getServerPortPage(Map<String, Object> searchParams, int pageNumber, int pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
		Specification<ServerPort> spec = buildSpecification(searchParams);
		return serverPortDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建动态查询条件组合.
	 * 
	 * 自定义的查询在此进行组合.
	 * 
	 * @param searchParams
	 * @return Specification<ServerPort>
	 */
	private Specification<ServerPort> buildSpecification(Map<String, Object> searchParams) { // 将条件查询放入Map中.查询条件可查询SearchFilter类.
		searchParams.put("EQ_status", CMDBuildConstants.STATUS_ACTIVE);
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<ServerPort> spec = DynamicSpecifications.bySearchFilter(filters.values(), ServerPort.class);
		return spec;
	}

	/**
	 * ServerPortDTO webservice分页查询.
	 * 
	 * 将Page<T>重新组织成符合DTO格式的分页格式对象. * @param searchParams 查询语句Map.
	 * 
	 * @param pageNumber
	 *            当前页数,最小为1.
	 * @param pageSize
	 *            当前页大小,如每页为10行
	 * @return PaginationResult<ServerPortDTO>
	 */
	public PaginationResult<ServerPortDTO> getServerPortDTOPagination(Map<String, Object> searchParams, int pageNumber,
			int pageSize) {
		Page<ServerPort> page = getServerPortPage(searchParams, pageNumber, pageSize); // 将List<ServerPort>中的数据转换为List<ServerPortDTO>
		List<ServerPortDTO> dtos = BeanMapper.mapList(page.getContent(), ServerPortDTO.class);
		PaginationResult<ServerPortDTO> paginationResult = new PaginationResult<ServerPortDTO>(page.getNumber(),
				page.getSize(), page.getTotalPages(), page.getNumberOfElements(), page.getNumberOfElements(),
				page.hasPreviousPage(), page.isFirstPage(), page.hasNextPage(), page.isLastPage(), dtos);
		return paginationResult;
	}
}