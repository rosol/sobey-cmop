package com.sobey.cmdbuild.service.organisation;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmdbuild.constants.CMDBuildConstants;
import com.sobey.cmdbuild.entity.GroupPolicyHistory;
import com.sobey.cmdbuild.repository.GroupPolicyHistoryDao;
import com.sobey.cmdbuild.service.BasicSevcie;
import com.sobey.cmdbuild.webservice.response.dto.GroupPolicyHistoryDTO;
import com.sobey.cmdbuild.webservice.response.result.PaginationResult;
import com.sobey.core.mapper.BeanMapper;
import com.sobey.core.persistence.DynamicSpecifications;
import com.sobey.core.persistence.SearchFilter;

/**
 * GroupPolicyHistory的service类.
 */
@Service
@Transactional
public class GroupPolicyHistoryService extends BasicSevcie {
	@Autowired
	private GroupPolicyHistoryDao groupPolicyHistoryDao;

	/**
	 * 根据ID获得对象
	 * 
	 * @param id
	 * @return GroupPolicyHistory
	 */
	public GroupPolicyHistory findGroupPolicyHistory(Integer id) {
		return groupPolicyHistoryDao.findOne(id);
	}

	/**
	 * 新增、保存对象
	 * 
	 * @param groupPolicyHistory
	 * @return GroupPolicyHistory
	 */
	public GroupPolicyHistory saveOrUpdate(GroupPolicyHistory groupPolicyHistory) {
		return groupPolicyHistoryDao.save(groupPolicyHistory);
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public void deleteGroupPolicyHistory(Integer id) {
		groupPolicyHistoryDao.delete(id);
	}

	/**
	 * 根据code获得状态为"A"的有效对象
	 * 
	 * @param code
	 * @return GroupPolicyHistory
	 */
	public GroupPolicyHistory findByCode(String code) {
		return groupPolicyHistoryDao.findByCodeAndStatus(code, CMDBuildConstants.STATUS_ACTIVE);
	}

	/**
	 * 获得所有对象集合
	 * 
	 * @return List<GroupPolicyHistory>
	 */
	public List<GroupPolicyHistory> getCompanies() {
		return groupPolicyHistoryDao.findAllByStatus(CMDBuildConstants.STATUS_ACTIVE);
	}

	/**
	 * Spring-data-jpa自带的分页查询
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<GroupPolicyHistory>
	 */
	private Page<GroupPolicyHistory> getGroupPolicyHistoryPage(Map<String, Object> searchParams, int pageNumber,
			int pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
		Specification<GroupPolicyHistory> spec = buildSpecification(searchParams);
		return groupPolicyHistoryDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建动态查询条件组合.
	 * 
	 * 自定义的查询在此进行组合.
	 * 
	 * @param searchParams
	 * @return Specification<GroupPolicyHistory>
	 */
	private Specification<GroupPolicyHistory> buildSpecification(Map<String, Object> searchParams) { // 将条件查询放入Map中.查询条件可查询SearchFilter类.
		searchParams.put("EQ_status", CMDBuildConstants.STATUS_ACTIVE);
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<GroupPolicyHistory> spec = DynamicSpecifications.bySearchFilter(filters.values(),
				GroupPolicyHistory.class);
		return spec;
	}

	/**
	 * GroupPolicyHistoryDTO webservice分页查询.
	 * 
	 * 将Page<T>重新组织成符合DTO格式的分页格式对象. * @param searchParams 查询语句Map.
	 * 
	 * @param pageNumber
	 *            当前页数,最小为1.
	 * @param pageSize
	 *            当前页大小,如果每页为10行
	 * @return PaginationResult<GroupPolicyHistoryDTO>
	 */
	public PaginationResult<GroupPolicyHistoryDTO> getGroupPolicyHistoryDTOPagination(Map<String, Object> searchParams,
			int pageNumber, int pageSize) {
		Page<GroupPolicyHistory> page = getGroupPolicyHistoryPage(searchParams, pageNumber, pageSize); // 将List<GroupPolicyHistory>中的数据转换为List<GroupPolicyHistoryDTO>
		List<GroupPolicyHistoryDTO> dtos = BeanMapper.mapList(page.getContent(), GroupPolicyHistoryDTO.class);
		PaginationResult<GroupPolicyHistoryDTO> paginationResult = new PaginationResult<GroupPolicyHistoryDTO>(
				page.getNumber(), page.getSize(), page.getTotalPages(), page.getNumberOfElements(),
				page.getNumberOfElements(), page.hasPreviousPage(), page.isFirstPage(), page.hasNextPage(),
				page.isLastPage(), dtos);
		return paginationResult;
	}
}