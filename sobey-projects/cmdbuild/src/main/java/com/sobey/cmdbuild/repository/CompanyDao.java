package com.sobey.cmdbuild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

	List<Company> findAllByStatus(Character character);

	Company findByCodeAndStatus(String code, Character character);

}
