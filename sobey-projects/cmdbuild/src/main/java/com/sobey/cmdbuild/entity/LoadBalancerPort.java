package com.sobey.cmdbuild.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.PortBasic;

/**
 * LoadBalancerPort generated by hbm2java
 */
@Entity
@Table(name = "load_balancer_port", schema = "public")
public class LoadBalancerPort extends PortBasic {

	private Integer loadBalancer;
	private Set<LoadBalancerPortHistory> loadBalancerPortHistories = new HashSet<LoadBalancerPortHistory>(0);

	public LoadBalancerPort() {
	}

	@Column(name = "\"loadBalancer\"")
	public Integer getLoadBalancer() {
		return loadBalancer;
	}

	public void setLoadBalancer(Integer loadBalancer) {
		this.loadBalancer = loadBalancer;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadBalancerPort")
	public Set<LoadBalancerPortHistory> getLoadBalancerPortHistories() {
		return loadBalancerPortHistories;
	}

	public void setLoadBalancerPortHistories(Set<LoadBalancerPortHistory> loadBalancerPortHistories) {
		this.loadBalancerPortHistories = loadBalancerPortHistories;
	}

}
