package com.sobey.cmdbuild.service.iaas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sobey.cmdbuild.constants.CMDBuildConstants;
import com.sobey.cmdbuild.entity.DnsPolicy;
import com.sobey.cmdbuild.repository.DnsPolicyDao;
import com.sobey.cmdbuild.service.BasicSevcie;
import com.sobey.cmdbuild.webservice.response.dto.DnsPolicyDTO;
import com.sobey.cmdbuild.webservice.response.result.PaginationResult;
import com.sobey.core.mapper.BeanMapper;
import com.sobey.core.persistence.DynamicSpecifications;
import com.sobey.core.persistence.SearchFilter;

/**
 * DnsPolicy的service类.
 */
@Service
@Transactional
public class DnsPolicyService extends BasicSevcie {

	@Autowired
	private DnsPolicyDao dnsPolicyDao;

	/**
	 * 创建动态查询条件组合.
	 * 
	 * 自定义的查询在此进行组合.默认获得状态为"A"的有效对象.
	 * 
	 * @param searchParams
	 * @return Specification<DnsPolicy>
	 */
	private Specification<DnsPolicy> buildSpecification(Map<String, Object> searchParams) {

		searchParams.put("EQ_status", CMDBuildConstants.STATUS_ACTIVE);

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		return DynamicSpecifications.bySearchFilter(filters.values(), DnsPolicy.class);
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public void deleteDnsPolicy(Integer id) {
		dnsPolicyDao.delete(id);
	}

	/**
	 * 根据ID获得对象
	 * 
	 * @param id
	 * @return DnsPolicy
	 */
	public DnsPolicy findDnsPolicy(Integer id) {
		return dnsPolicyDao.findOne(id);
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
	 * @return DnsPolicy
	 */
	public DnsPolicy findDnsPolicy(Map<String, Object> searchParams) {
		return dnsPolicyDao.findOne(buildSpecification(searchParams));
	}

	/**
	 * DnsPolicyDTO webservice分页查询.
	 * 
	 * 将Page<T>重新组织成符合DTO格式的分页格式对象.
	 * 
	 * @param searchParams
	 *            查询语句Map.
	 * @param pageNumber
	 *            当前页数,最小为1.
	 * @param pageSize
	 *            当前页大小,如每页为10行
	 * @return PaginationResult<DnsPolicyDTO>
	 */
	public PaginationResult<DnsPolicyDTO> getDnsPolicyDTOPagination(Map<String, Object> searchParams, int pageNumber,
			int pageSize) {

		Page<DnsPolicy> page = getDnsPolicyPage(searchParams, pageNumber, pageSize);

		List<DnsPolicyDTO> dtos = BeanMapper.mapList(page.getContent(), DnsPolicyDTO.class);

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
	 * @return List<DnsPolicy>
	 */
	public List<DnsPolicy> getDnsPolicyList(Map<String, Object> searchParams) {
		return dnsPolicyDao.findAll(buildSpecification(searchParams));
	}

	/**
	 * Spring-data-jpa自带的分页查询
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<DnsPolicy>
	 */
	private Page<DnsPolicy> getDnsPolicyPage(Map<String, Object> searchParams, int pageNumber, int pageSize) {

		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

		Specification<DnsPolicy> spec = buildSpecification(searchParams);

		return dnsPolicyDao.findAll(spec, pageRequest);
	}

	/**
	 * 新增、保存对象
	 * 
	 * @param dnsPolicy
	 * @return DnsPolicy
	 */
	public DnsPolicy saveOrUpdate(DnsPolicy dnsPolicy) {
		return dnsPolicyDao.save(dnsPolicy);
	}
}
