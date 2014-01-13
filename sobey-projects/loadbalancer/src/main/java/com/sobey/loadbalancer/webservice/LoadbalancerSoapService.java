package com.sobey.loadbalancer.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.sobey.loadbalancer.constans.WsConstants;
import com.sobey.loadbalancer.webservice.response.dto.ELBParameter;
import com.sobey.loadbalancer.webservice.response.result.WSResult;

/**
 * DNS对外暴露的唯一的webservice接口.
 * 
 * @author Administrator
 * 
 */
@WebService(name = "LoadbalancerSoapService", targetNamespace = WsConstants.NS)
public interface LoadbalancerSoapService {

	/**
	 * 在Loadbalancer上执行脚本,创建ELB
	 * 
	 * @param parameter
	 *            {@link ELBParameter}
	 * @return
	 */
	WSResult createELBByLoadbalancer(@WebParam(name = "ELBParameter") ELBParameter parameter);

	/**
	 * 在Loadbalancer上执行脚本,删除ELB
	 * 
	 * @param parameter
	 *            {@link ELBParameter}
	 * @return
	 */
	WSResult deleteELBByLoadbalancer(@WebParam(name = "ELBParameter") ELBParameter parameter);

	/**
	 * 在Loadbalancer上执行脚本,删除ELB Port
	 * 
	 * @param parameter
	 *            {@link ELBParameter}
	 * @return
	 */
	WSResult deleteELBPortByLoadbalancer(@WebParam(name = "ELBParameter") ELBParameter parameter);

}
