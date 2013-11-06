package com.sobey.cmdbuild.service.financial;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sobey.cmdbuild.constants.CMDBuildConstants;
import com.sobey.cmdbuild.entity.DeviceSpec;
import com.sobey.cmdbuild.repository.DeviceSpecDao;
import com.sobey.cmdbuild.service.BasicSevcie;
import com.sobey.cmdbuild.webservice.response.dto.DeviceSpecDTO;
import com.sobey.cmdbuild.webservice.response.result.PaginationResult;
import com.sobey.core.mapper.BeanMapper;
import com.sobey.core.persistence.DynamicSpecifications;
import com.sobey.core.persistence.SearchFilter;

/**
 * DeviceSpec的service类.
 */
@Service
@Transactional
public class DeviceSpecService extends BasicSevcie {
	@Autowired
	private DeviceSpecDao deviceSpecDao;

	/**
	 * 根据ID获得对象
	 * 
	 * @param id
	 * @return DeviceSpec
	 */
	public DeviceSpec findDeviceSpec(Integer id) {
		return deviceSpecDao.findOne(id);
	}

	/**
	 * 新增、保存对象
	 * 
	 * @param deviceSpec
	 * @return DeviceSpec
	 */
	public DeviceSpec saveOrUpdate(DeviceSpec deviceSpec) {
		return deviceSpecDao.save(deviceSpec);
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	public void deleteDeviceSpec(Integer id) {
		deviceSpecDao.delete(id);
	}

	/**
	 * 根据code获得状态为"A"的有效对象
	 * 
	 * @param code
	 * @return DeviceSpec
	 */
	public DeviceSpec findByCode(String code) {
		return deviceSpecDao.findByCodeAndStatus(code, CMDBuildConstants.STATUS_ACTIVE);
	}

	/**
	 * 获得所有对象集合
	 * 
	 * @return List<DeviceSpec>
	 */
	public List<DeviceSpec> getCompanies() {
		return deviceSpecDao.findAllByStatus(CMDBuildConstants.STATUS_ACTIVE);
	}

	/**
	 * Spring-data-jpa自带的分页查询
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<DeviceSpec>
	 */
	private Page<DeviceSpec> getDeviceSpecPage(Map<String, Object> searchParams, int pageNumber, int pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
		Specification<DeviceSpec> spec = buildSpecification(searchParams);
		return deviceSpecDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建动态查询条件组合.
	 * 
	 * 自定义的查询在此进行组合.
	 * 
	 * @param searchParams
	 * @return Specification<DeviceSpec>
	 */
	private Specification<DeviceSpec> buildSpecification(Map<String, Object> searchParams) { // 将条件查询放入Map中.查询条件可查询SearchFilter类.
		searchParams.put("EQ_status", CMDBuildConstants.STATUS_ACTIVE);
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<DeviceSpec> spec = DynamicSpecifications.bySearchFilter(filters.values(), DeviceSpec.class);
		return spec;
	}

	/**
	 * DeviceSpecDTO webservice分页查询.
	 * 
	 * 将Page<T>重新组织成符合DTO格式的分页格式对象. * @param searchParams 查询语句Map.
	 * 
	 * @param pageNumber
	 *            当前页数,最小为1.
	 * @param pageSize
	 *            当前页大小,如每页为10行
	 * @return PaginationResult<DeviceSpecDTO>
	 */
	public PaginationResult<DeviceSpecDTO> getDeviceSpecDTOPagination(Map<String, Object> searchParams, int pageNumber,
			int pageSize) {
		Page<DeviceSpec> page = getDeviceSpecPage(searchParams, pageNumber, pageSize); // 将List<DeviceSpec>中的数据转换为List<DeviceSpecDTO>
		List<DeviceSpecDTO> dtos = BeanMapper.mapList(page.getContent(), DeviceSpecDTO.class);
		PaginationResult<DeviceSpecDTO> paginationResult = new PaginationResult<DeviceSpecDTO>(page.getNumber(),
				page.getSize(), page.getTotalPages(), page.getNumberOfElements(), page.getNumberOfElements(),
				page.hasPreviousPage(), page.isFirstPage(), page.hasNextPage(), page.isLastPage(), dtos);
		return paginationResult;
	}
}