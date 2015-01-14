package com.sobey.api.restful;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sobey.api.entity.DnsEntity;
import com.sobey.api.entity.EcsEntity;
import com.sobey.api.entity.Es3Entity;
import com.sobey.api.entity.FirewallServiceEntity;
import com.sobey.api.entity.RouterEntity;
import com.sobey.api.entity.SubnetEntity;
import com.sobey.api.entity.TenantsEntity;
import com.sobey.api.service.RestfulService;
import com.sobey.api.webservice.response.result.DTOResult;
import com.sobey.api.webservice.response.result.WSResult;

@RestController
public class ApiController {

	@Autowired
	private RestfulService servie;

	private String URLEscape(String value) {
		try {
			return new String(value.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/********** Tenants ***********/

	@RequestMapping(value = "/TenantsResult/{accessKey}", method = RequestMethod.GET)
	public DTOResult<TenantsEntity> TenantsResult(@PathVariable("accessKey") String accessKey) {
		return servie.findTenants(accessKey);
	}

	@RequestMapping(value = "/createTenants/", method = RequestMethod.POST)
	public WSResult createTenants(@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "phone", required = false) String phone) {
		return servie.createTenants(company, name, email, password, phone);
	}

	/********** Subnet ***********/

	@RequestMapping(value = "/SubnetResult/{code}/{accessKey}", method = RequestMethod.GET)
	public DTOResult<SubnetEntity> SubnetResult(@PathVariable("code") String code,
			@PathVariable("accessKey") String accessKey) {
		return servie.findSubnet(code, accessKey);
	}

	@RequestMapping(value = "/createSubnet/", method = RequestMethod.POST)
	public WSResult createSubnet(@RequestParam(value = "subnetName") String subnetName,
			@RequestParam(value = "segment") String segment, @RequestParam(value = "gateway") String gateway,
			@RequestParam(value = "netmask") String netmask, @RequestParam(value = "idc") String idc,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.createSubnet(subnetName, segment, gateway, netmask, idc, accessKey);
	}

	/********** Router ***********/
	@RequestMapping(value = "/RouterResult/{code}/{accessKey}", method = RequestMethod.GET)
	public DTOResult<RouterEntity> RouterResult(@PathVariable("code") String code,
			@PathVariable("accessKey") String accessKey) {
		return servie.findRouter(code, accessKey);
	}

	@RequestMapping(value = "/createRouter/", method = RequestMethod.POST)
	public WSResult createRouter(@RequestParam(value = "routerName") String routerName,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "routerSpec") String routerSpec,
			@RequestParam(value = "subnetCode") String subnetCode, @RequestParam(value = "idc") String idc,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.createRouter(routerName, subnetCode, remark, routerSpec, idc, accessKey);
	}

	/********** ECS ***********/
	@RequestMapping(value = "/ECSResult/{code}/{accessKey}", method = RequestMethod.GET)
	public DTOResult<EcsEntity> ECSResult(@PathVariable("code") String code, @PathVariable("accessKey") String accessKey) {
		return servie.findECS(URLEscape(code), accessKey);
	}

	@RequestMapping(value = "/createECS/", method = RequestMethod.POST)
	public WSResult createECS(@RequestParam(value = "ecsName") String ecsName,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "ecsSpec") String ecsSpec,
			@RequestParam(value = "subnetCode") String subnetCode, @RequestParam(value = "idc") String idc,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.createECS(ecsName, subnetCode, remark, ecsSpec, idc, accessKey);
	}

	@RequestMapping(value = "/destroyECS/", method = RequestMethod.POST)
	public WSResult destroyECS(@RequestParam(value = "code") String code,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.destroyECS(code, accessKey);
	}

	@RequestMapping(value = "/powerOpsECS/", method = RequestMethod.POST)
	public WSResult powerOpsECS(@RequestParam(value = "code") String code,
			@RequestParam(value = "powerOperation") String powerOperation,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.powerOpsECS(code, powerOperation, accessKey);
	}

	@RequestMapping(value = "/reconfigECS/", method = RequestMethod.POST)
	public WSResult reconfigECS(@RequestParam(value = "code") String code,
			@RequestParam(value = "ecsSpec") String ecsSpec, @RequestParam(value = "accessKey") String accessKey) {
		return servie.reconfigECS(code, ecsSpec, accessKey);
	}

	/********** ES3 ***********/
	@RequestMapping(value = "/ES3Result/{code}/{accessKey}", method = RequestMethod.GET)
	public DTOResult<Es3Entity> ES3Result(@PathVariable("code") String code, @PathVariable("accessKey") String accessKey) {
		return servie.findES3(URLEscape(code), accessKey);
	}

	@RequestMapping(value = "/createES3/", method = RequestMethod.POST)
	public WSResult createES3(@RequestParam(value = "es3Name") String es3Name,
			@RequestParam(value = "es3Size") Double es3Size, @RequestParam(value = "es3Type") String es3Type,
			@RequestParam(value = "idc") String idc, @RequestParam(value = "ecsCode") String ecsCode,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "accessKey") String accessKey) {
		return servie.createES3(es3Name, es3Size, es3Type, idc, ecsCode, remark, accessKey);
	}

	@RequestMapping(value = "/deleteES3/", method = RequestMethod.POST)
	public WSResult deleteES3(@RequestParam(value = "code") String code,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.deleteES3(code, accessKey);
	}

	/********** DNS ***********/
	@RequestMapping(value = "/DNSResult/{code}/{accessKey}", method = RequestMethod.GET)
	public DTOResult<DnsEntity> DNSResult(@PathVariable("code") String code, @PathVariable("accessKey") String accessKey) {
		return servie.findDNS(URLEscape(code), accessKey);
	}

	@RequestMapping(value = "/createDNS/", method = RequestMethod.POST)
	public WSResult createDNS(@RequestParam(value = "domainName") String domainName,
			@RequestParam(value = "eipCodes") String eipCodes, @RequestParam(value = "protocols") String protocols,
			@RequestParam(value = "remark", required = false) String remark, @RequestParam(value = "idc") String idc,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.createDNS(domainName, eipCodes, protocols, idc, remark, accessKey);
	}

	@RequestMapping(value = "/deleteDNS/", method = RequestMethod.POST)
	public WSResult deleteDNS(@RequestParam(value = "code") String code,
			@RequestParam(value = "accessKey") String accessKey) {
		return servie.deleteDNS(code, accessKey);
	}

	/******** FirewallService ********/
	@RequestMapping(value = "/FirewallServiceResult/{code}/{accessKey}", method = RequestMethod.GET)
	public DTOResult<FirewallServiceEntity> firewallServiceResult(@PathVariable("code") String code,
			@PathVariable("accessKey") String accessKey) {
		return servie.findFirewallService(code, accessKey);
	}

	@RequestMapping(value = "/createFirewallServiceResult/", method = RequestMethod.POST)
	public WSResult createFirewallServiceResult(
			@RequestParam(value = "firewallServiceName") String firewallServiceName,
			@RequestParam(value = "directions") String directions,
			@RequestParam(value = "rulesNames") String rulesNames, @RequestParam(value = "protocols") String protocols,
			@RequestParam(value = "actions") String actions, @RequestParam(value = "startPorts") String startPorts,
			@RequestParam(value = "endPorts") String endPorts, @RequestParam(value = "ipaddresses") String ipaddresses,
			@RequestParam(value = "idc") String idc, @RequestParam(value = "accessKey") String accessKey) {
		return servie.createFirewallService(firewallServiceName, directions, rulesNames, protocols, actions,
				startPorts, endPorts, ipaddresses, idc, accessKey);
	}

}
