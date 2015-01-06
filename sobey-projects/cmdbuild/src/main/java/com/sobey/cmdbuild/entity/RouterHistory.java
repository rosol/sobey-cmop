package com.sobey.cmdbuild.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sobey.cmdbuild.entity.basic.ServiceHistoryBasic;

/**
 * DnsHistory generated by hbm2java
 */
@Entity
@Table(name = "router_history", schema = "public")
public class RouterHistory extends ServiceHistoryBasic {

	private Router router;

	public RouterHistory() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CurrentId\"", nullable = false)
	public Router getRouter() {
		return router;
	}

	public void setRouter(Router router) {
		this.router = router;
	}

}