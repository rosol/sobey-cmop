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
import com.sobey.cmdbuild.entity.LoadBalancerPort;
import com.sobey.cmdbuild.repository.LoadBalancerPortDao;
import com.sobey.cmdbuild.service.BasicSevcie;
import com.sobey.cmdbuild.webservice.response.dto.LoadBalancerPortDTO;
import com.sobey.cmdbuild.webservice.response.result.PaginationResult;
import com.sobey.core.mapper.BeanMapper;
import com.sobey.core.persistence.DynamicSpecifications;
import com.sobey.core.persistence.SearchFilter;

/**
 * LoadBalancerPort的service类.
 */
@Service
@Transactional
public class LoadBalancerPortService extends BasicSevcie {

	@Autowired
	private LoadBalancerPortDao loadBalancerPortDao;

	/**
	 * 创建动态查询条件组合.
	 * 
	 * 自定义的查询在此进行组合.默认获得状态为"A"的有效对象.
	 * 
	 * @param searchParams
	 * @return Specification<LoadBalancerPort>
	 */
	private Specification<LoadBalancerPort> buildSpecification(Map<String, Object> searchParams) {

		searchParams.put("EQ_status", CMDBuildConstants.STATUS_ACTIVE);

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		return DynamicSpecifications.bySearchFilter(filters.values(), LoadBalancerPort.class);
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public void deleteLoadBalancerPort(Integer id) {
		loadBalancerPortDao.delete(id);
	}

	/**
	 * 根据ID获得对象
	 * 
	 * @param id
	 * @return LoadBalancerPort
	 */
	public LoadBalancerPort findLoadBalancerPort(Integer id) {
		return loadBalancerPortDao.findOne(id);
	}

	/**
	 * 根据自定义动态查询条件获得对象.
	 * 
	 * 将条件查询放入searchParams中. 查询条件可查询{@link SearchFilter}类.
	 * 
	 * <pre>
	 * searchParams.put(&quot;EQ_status&quot;, 'A');
	 * </pre>
	 * 
	 * @param searchParams
	 *            动态查询条件Map
	 * @return LoadBalancerPort
	 */
	public LoadBalancerPort findLoadBalancerPort(Map<String, Object> searchParams) {
		return loadBalancerPortDao.findOne(buildSpecification(searchParams));
	}

	/**
	 * LoadBalancerPortDTO webservice分页查询.
	 * 
	 * 将Page<T>重新组织成符合DTO格式的分页格式对象.
	 * 
	 * @param searchParams
	 *            查询语句Map.
	 * @param pageNumber
	 *            当前页数,最小为1.
	 * @param pageSize
	 *            当前页大小,如每页为10行
	 * @return PaginationResult<LoadBalancerPortDTO>
	 */
	public PaginationResult<LoadBalancerPortDTO> getLoadBalancerPortDTOPagination(Map<String, Object> searchParams,
			int pageNumber, int pageSize) {

		Page<LoadBalancerPort> page = getLoadBalancerPortPage(searchParams, pageNumber, pageSize);

		List<LoadBalancerPortDTO> dtos = BeanMapper.mapList(page.getContent(), LoadBalancerPortDTO.class);

		return fillPaginationResult(page, dtos);
	}

	/**
	 * 根据自定义动态查询条件获得对象集合.
	 * 
	 * 将条件查询放入searchParams中. 查询条件可查询{@link SearchFilter}类.
	 * 
	 * <pre>
	 * searchParams.put(&quot;EQ_status&quot;, 'A');
	 * </pre>
	 * 
	 * @param searchParams
	 *            动态查询条件Map
	 * @return List<LoadBalancerPort>
	 */
	public List<LoadBalancerPort> getLoadBalancerPortList(Map<String, Object> searchParams) {
		return loadBalancerPortDao.findAll(buildSpecification(searchParams));
	}

	/**
	 * Spring-data-jpa自带的分页查询
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<LoadBalancerPort>
	 */
	private Page<LoadBalancerPort> getLoadBalancerPortPage(Map<String, Object> searchParams, int pageNumber,
			int pageSize) {

		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

		Specification<LoadBalancerPort> spec = buildSpecification(searchParams);

		return loadBalancerPortDao.findAll(spec, pageRequest);
	}

	/**
	 * 新增、保存对象
	 * 
	 * @param LoadBalancerPort
	 * @return LoadBalancerPort
	 */
	public LoadBalancerPort saveOrUpdate(LoadBalancerPort loadBalancerPort) {
		return loadBalancerPortDao.save(loadBalancerPort);
	}
}