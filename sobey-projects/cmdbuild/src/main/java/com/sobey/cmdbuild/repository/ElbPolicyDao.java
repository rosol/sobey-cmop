package com.sobey.cmdbuild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.ElbPolicy;

public interface ElbPolicyDao extends PagingAndSortingRepository<ElbPolicy, Integer>,
		JpaSpecificationExecutor<ElbPolicy> {

	List<ElbPolicy> findAllByStatus(Character character);

	ElbPolicy findByCodeAndStatus(String code, Character character);
}