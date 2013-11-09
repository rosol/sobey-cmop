package com.sobey.cmdbuild.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.CompanyHistory;

public interface CompanyHistoryDao extends PagingAndSortingRepository<CompanyHistory, Integer>,
		JpaSpecificationExecutor<CompanyHistory> {

}