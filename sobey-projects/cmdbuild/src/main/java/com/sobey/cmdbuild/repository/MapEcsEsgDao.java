package com.sobey.cmdbuild.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sobey.cmdbuild.entity.MapEcsEsg;

public interface MapEcsEsgDao extends PagingAndSortingRepository<MapEcsEsg, Integer>,
		JpaSpecificationExecutor<MapEcsEsg> {

}