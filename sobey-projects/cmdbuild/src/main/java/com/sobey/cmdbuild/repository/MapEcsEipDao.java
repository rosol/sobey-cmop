package com.sobey.cmdbuild.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.MapEcsEip;

public interface MapEcsEipDao extends PagingAndSortingRepository<MapEcsEip, Integer>,
		JpaSpecificationExecutor<MapEcsEip> {

}