package com.sobey.cmdbuild.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.EcsSpec;

public interface EcsSpecDao extends PagingAndSortingRepository<EcsSpec, Integer>, JpaSpecificationExecutor<EcsSpec> {

}