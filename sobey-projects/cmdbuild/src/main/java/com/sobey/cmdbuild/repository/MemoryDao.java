package com.sobey.cmdbuild.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.Memory;

public interface MemoryDao extends PagingAndSortingRepository<Memory, Integer>, JpaSpecificationExecutor<Memory> {

}