package com.sobey.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sobey.api.constans.LookUpConstants;
import com.sobey.api.entity.DnsEntity;
import com.sobey.api.entity.EcsEntity;
import com.sobey.api.entity.EipEntity;
import com.sobey.api.entity.ElbEntity;
import com.sobey.api.entity.Es3Entity;
import com.sobey.api.entity.FirewallServiceEntity;
import com.sobey.api.entity.RouterEntity;
import com.sobey.api.entity.SubnetEntity;
import com.sobey.api.entity.TenantsEntity;
import com.sobey.api.utils.CMDBuildUtil;
import com.sobey.api.webservice.response.result.DTOResult;
import com.sobey.api.webservice.response.result.WSResult;
import com.sobey.generate.cmdbuild.CmdbuildSoapService;
import com.sobey.generate.cmdbuild.ConfigFirewallServiceCategoryDTO;
import com.sobey.generate.cmdbuild.DnsDTO;
import com.sobey.generate.cmdbuild.DnsPolicyDTO;
import com.sobey.generate.cmdbuild.EcsDTO;
import com.sobey.generate.cmdbuild.EcsSpecDTO;
import com.sobey.generate.cmdbuild.EipDTO;
import com.sobey.generate.cmdbuild.ElbDTO;
import com.sobey.generate.cmdbuild.ElbPolicyDTO;
import com.sobey.generate.cmdbuild.Es3DTO;
import com.sobey.generate.cmdbuild.FirewallServiceDTO;
import com.sobey.generate.cmdbuild.IdcDTO;
import com.sobey.generate.cmdbuild.IpaddressDTO;
import com.sobey.generate.cmdbuild.LookUpDTO;
import com.sobey.generate.cmdbuild.MapEcsElbDTO;
import com.sobey.generate.cmdbuild.MapEipDnsDTO;
import com.sobey.generate.cmdbuild.RouterDTO;
import com.sobey.generate.cmdbuild.SubnetDTO;
import com.sobey.generate.cmdbuild.TenantsDTO;
import com.sobey.generate.dns.DnsSoapService;
import com.sobey.generate.firewall.FirewallSoapService;
import com.sobey.generate.instance.InstanceSoapService;
import com.sobey.generate.loadbalancer.LoadbalancerSoapService;
import com.sobey.generate.storage.StorageSoapService;
import com.sobey.generate.switches.SwitchesSoapService;
import com.sobey.generate.zabbix.ZabbixSoapService;

@Service
public class RestfulServiceImpl implements RestfulService {

	@Autowired
	private CmdbuildSoapService cmdbuildSoapService;
	@Autowired
	private FirewallSoapService firewallSoapService;
	@Autowired
	private SwitchesSoapService switchesSoapService;
	@Autowired
	private InstanceSoapService instanceSoapService;
	@Autowired
	private StorageSoapService storageSoapService;
	@Autowired
	private LoadbalancerSoapService loadbalancerSoapService;
	@Autowired
	private DnsSoapService dnsSoapService;
	@Autowired
	private ZabbixSoapService zabbixSoapService;
	@Autowired
	private ApiService apiService;

	private IdcDTO findIdcDTO(String description) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_description", description);
		return (IdcDTO) cmdbuildSoapService.findIdcByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private TenantsDTO findTenantsDTO(String accessKey) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_accessKey", accessKey);
		return (TenantsDTO) cmdbuildSoapService.findTenantsByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private EcsDTO findEcsDTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (EcsDTO) cmdbuildSoapService.findEcsByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private Es3DTO findEs3DTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (Es3DTO) cmdbuildSoapService.findEs3ByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private EipDTO findEipDTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (EipDTO) cmdbuildSoapService.findEipByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private ElbDTO findElbDTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (ElbDTO) cmdbuildSoapService.findElbByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private DnsDTO findDnsDTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (DnsDTO) cmdbuildSoapService.findDnsByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private SubnetDTO findSubnetDTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (SubnetDTO) cmdbuildSoapService.findSubnetByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private RouterDTO findRouterDTO(Integer tenantsId, String code) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_tenants", tenantsId);
		map.put("EQ_code", code);
		return (RouterDTO) cmdbuildSoapService.findRouterByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private EcsSpecDTO findEcsSpecDTO(String description) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_description", description);
		return (EcsSpecDTO) cmdbuildSoapService.findEcsSpecByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private LookUpDTO findLookUpDTO(String description, String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_description", description);
		map.put("EQ_type", type);
		return (LookUpDTO) cmdbuildSoapService.findLookUpByParams(CMDBuildUtil.wrapperSearchParams(map)).getDto();
	}

	private static Integer getPortFromProtocol(String protocol) {
		return "HTTPS".equals(protocol.toUpperCase()) ? 443 : 80;
	}

	@Override
	public DTOResult<TenantsEntity> findTenants(String accessKey) {

		DTOResult<TenantsEntity> result = new DTOResult<TenantsEntity>();

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);

		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "租户不存在.");
			return result;
		}

		TenantsEntity entity = new TenantsEntity(tenantsDTO.getCode(), tenantsDTO.getDescription(),
				tenantsDTO.getEmail(), tenantsDTO.getPhone(), tenantsDTO.getCompany());
		result.setDto(entity);

		return result;
	}

	@Override
	public WSResult createTenants(String company, String name, String email, String password, String phone) {

		TenantsDTO tenantsDTO = new TenantsDTO();

		tenantsDTO.setCompany(company);
		tenantsDTO.setDescription(email);
		tenantsDTO.setEmail(email);
		tenantsDTO.setPassword(password);
		tenantsDTO.setPhone(phone);

		WSResult result = new WSResult();

		result.setMessage(apiService.createTenants(tenantsDTO).getMessage());

		return result;

	}

	@Override
	public DTOResult<SubnetEntity> findSubnet(String code, String accessKey) {

		DTOResult<SubnetEntity> result = new DTOResult<SubnetEntity>();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		SubnetDTO subnetDTO = findSubnetDTO(tenantsDTO.getId(), code);
		if (subnetDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "Subnet不存在.");
			return result;
		}

		IdcDTO idcDTO = (IdcDTO) cmdbuildSoapService.findIdc(subnetDTO.getIdc()).getDto();

		SubnetEntity entity = new SubnetEntity(idcDTO.getDescription(), subnetDTO.getGateway(), subnetDTO.getCode(),
				subnetDTO.getNetMask(), subnetDTO.getRemark(), subnetDTO.getSegment(), subnetDTO.getDescription());
		result.setDto(entity);
		return result;
	}

	@Override
	public WSResult createSubnet(String subnetName, String segment, String gateway, String netmask, String idc,
			String accessKey) {

		WSResult result = new WSResult();

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		IdcDTO idcDTO = findIdcDTO(idc);
		if (idcDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "IDC不存在.");
			return result;
		}

		SubnetDTO subnetDTO = new SubnetDTO();

		subnetDTO.setIdc(idcDTO.getId());
		subnetDTO.setGateway(gateway);
		subnetDTO.setNetMask(netmask);
		subnetDTO.setTenants(tenantsDTO.getId());
		subnetDTO.setSegment(segment);
		subnetDTO.setDescription(subnetName);

		result.setMessage(apiService.createSubnet(subnetDTO).getMessage());

		return result;

	}

	@Override
	public DTOResult<EcsEntity> findECS(String code, String accessKey) {

		DTOResult<EcsEntity> result = new DTOResult<EcsEntity>();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		EcsDTO ecsDTO = findEcsDTO(tenantsDTO.getId(), code);
		if (ecsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ECS不存在.");
			return result;
		}

		IdcDTO idcDTO = (IdcDTO) cmdbuildSoapService.findIdc(ecsDTO.getIdc()).getDto();
		IpaddressDTO ipaddressDTO = (IpaddressDTO) cmdbuildSoapService.findIpaddress(ecsDTO.getIpaddress()).getDto();
		EcsSpecDTO ecsSpecDTO = (EcsSpecDTO) cmdbuildSoapService.findEcsSpec(ecsDTO.getEcsSpec()).getDto();
		LookUpDTO lookUpDTO = (LookUpDTO) cmdbuildSoapService.findLookUp(ecsDTO.getEcsStatus()).getDto();

		EcsEntity entity = new EcsEntity(ecsDTO.getCpuNumber(), ecsDTO.getDescription(), idcDTO.getDescription(),
				ecsDTO.getCode(), ipaddressDTO.getDescription(), ecsDTO.isIsDesktop(), ecsDTO.isIsGpu(),
				ecsDTO.getMemorySize(), ecsDTO.getRemark(), ecsSpecDTO.getDescription(), lookUpDTO.getDescription());
		result.setDto(entity);
		return result;
	}

	@Override
	public WSResult createECS(String ecsName, String subnetCode, String remark, String ecsSpec, String idc,
			String accessKey) {

		WSResult result = new WSResult();

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		EcsSpecDTO ecsSpecDTO = findEcsSpecDTO(ecsSpec);
		if (ecsSpecDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "规格不存在.");
			return result;
		}

		IdcDTO idcDTO = findIdcDTO(idc);
		if (idcDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "IDC不存在.");
			return result;
		}

		SubnetDTO subnetDTO = findSubnetDTO(tenantsDTO.getId(), subnetCode);

		EcsDTO ecsDTO = new EcsDTO();
		ecsDTO.setAgentType(LookUpConstants.AgentType.VMware.getValue());
		ecsDTO.setDescription(ecsName);
		ecsDTO.setEcsSpec(ecsSpecDTO.getId());
		ecsDTO.setIdc(idcDTO.getId());
		ecsDTO.setRemark(remark);
		ecsDTO.setTenants(tenantsDTO.getId());
		ecsDTO.setSubnet(subnetDTO.getId());

		result.setMessage(apiService.createECS(ecsDTO).getMessage());
		return result;
	}

	@Override
	public WSResult destroyECS(String code, String accessKey) {

		WSResult result = new WSResult();

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		EcsDTO ecsDTO = findEcsDTO(tenantsDTO.getId(), code);
		if (ecsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ECS不存在.");
			return result;
		}
		apiService.destroyECS(ecsDTO.getId());
		result.setMessage("销毁成功.");
		return result;

	}

	@Override
	public WSResult powerOpsECS(String code, String powerOperation, String accessKey) {

		WSResult result = new WSResult();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		EcsDTO ecsDTO = findEcsDTO(tenantsDTO.getId(), code);
		if (ecsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ECS不存在.");
			return result;
		}
		return apiService.powerOpsECS(ecsDTO.getId(), powerOperation);
	}

	@Override
	public WSResult reconfigECS(String code, String ecsSpec, String accessKey) {

		WSResult result = new WSResult();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		EcsSpecDTO ecsSpecDTO = findEcsSpecDTO(ecsSpec);
		if (ecsSpecDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "规格不存在.");
			return result;
		}
		EcsDTO ecsDTO = findEcsDTO(tenantsDTO.getId(), code);
		if (ecsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ECS不存在.");
			return result;
		}
		return apiService.reconfigECS(ecsDTO.getId(), ecsSpecDTO.getId());
	}

	@Override
	public DTOResult<Es3Entity> findES3(String code, String accessKey) {

		DTOResult<Es3Entity> result = new DTOResult<Es3Entity>();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		Es3DTO es3DTO = findEs3DTO(tenantsDTO.getId(), code);
		if (es3DTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ES3不存在.");
			return result;
		}

		LookUpDTO lookUpDTO = (LookUpDTO) cmdbuildSoapService.findLookUp(es3DTO.getEs3Type()).getDto();

		Es3Entity entity = new Es3Entity(es3DTO.getTotalSize() + "GB", code, lookUpDTO.getDescription(),
				es3DTO.getCode(), es3DTO.getRemark());
		result.setDto(entity);
		return result;
	}

	@Override
	public WSResult createES3(String es3Name, Double es3Size, String es3Type, String idc, String ecsCode,
			String remark, String accessKey) {

		WSResult result = new WSResult();

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		IdcDTO idcDTO = findIdcDTO(idc);
		if (idcDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "IDC不存在.");
			return result;
		}

		LookUpDTO lookUpDTO = findLookUpDTO(es3Type, "ES3Type");
		if (lookUpDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ES3Type不存在.");
			return result;
		}

		EcsDTO ecsDTO = findEcsDTO(tenantsDTO.getId(), ecsCode);

		SubnetDTO subnetDTO = (SubnetDTO) cmdbuildSoapService.findSubnet(ecsDTO.getSubnet()).getDto();

		Es3DTO es3DTO = new Es3DTO();
		es3DTO.setAgentType(LookUpConstants.AgentType.VMware.getValue());
		es3DTO.setDescription(es3Name);
		es3DTO.setTotalSize(es3Size.toString());
		es3DTO.setEs3Type(lookUpDTO.getId());
		es3DTO.setIdc(idcDTO.getId());
		es3DTO.setVolumeName(es3Name);
		es3DTO.setTenants(tenantsDTO.getId());
		es3DTO.setRemark(remark);
		es3DTO.setSubnet(subnetDTO.getId());
		return apiService.createES3(es3DTO, ecsDTO.getId());
	}

	@Override
	public WSResult deleteES3(String code, String accessKey) {

		WSResult result = new WSResult();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		Es3DTO es3DTO = findEs3DTO(tenantsDTO.getId(), code);
		if (es3DTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ES3不存在.");
			return result;
		}
		result.setMessage("删除成功.");

		return apiService.deleteES3(es3DTO.getId());
	}

	@Override
	public DTOResult<ElbEntity> findELB(String code, String accessKey) {
		DTOResult<ElbEntity> result = new DTOResult<ElbEntity>();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		ElbDTO elbDTO = findElbDTO(tenantsDTO.getId(), code);
		if (elbDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ELB不存在.");
			return result;
		}
		List<EcsEntity> ecsEntities = new ArrayList<EcsEntity>();
		// 查询出ELB负载的ECS信息
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_idObj2", elbDTO.getId());
		List<Object> list = cmdbuildSoapService.getMapEcsElbList(CMDBuildUtil.wrapperSearchParams(map)).getDtoList()
				.getDto();
		for (Object object : list) {
			MapEcsElbDTO mapEcsElbDTO = (MapEcsElbDTO) object;
			EcsDTO ecsDTO = (EcsDTO) cmdbuildSoapService.findEcs(Integer.valueOf(mapEcsElbDTO.getIdObj1())).getDto();

			IdcDTO idcDTO = (IdcDTO) cmdbuildSoapService.findIdc(ecsDTO.getIdc()).getDto();
			IpaddressDTO ipaddressDTO = (IpaddressDTO) cmdbuildSoapService.findIpaddress(ecsDTO.getIpaddress())
					.getDto();
			EcsSpecDTO ecsSpecDTO = (EcsSpecDTO) cmdbuildSoapService.findEcsSpec(ecsDTO.getEcsSpec()).getDto();
			LookUpDTO lookUpDTO = (LookUpDTO) cmdbuildSoapService.findLookUp(ecsDTO.getEcsStatus()).getDto();

			EcsEntity entity = new EcsEntity(ecsDTO.getCpuNumber(), ecsDTO.getDescription(), idcDTO.getDescription(),
					ecsDTO.getCode(), ipaddressDTO.getDescription(), ecsDTO.isIsDesktop(), ecsDTO.isIsGpu(),
					ecsDTO.getMemorySize(), ecsDTO.getRemark(), ecsSpecDTO.getDescription(), lookUpDTO.getDescription());
			ecsEntities.add(entity);
		}
		ElbEntity entity = new ElbEntity(elbDTO.getCode(), code, ecsEntities);
		result.setDto(entity);
		return result;
	}

	@Override
	public WSResult createELB(String ecsIds, String protocols, String accessKey) {

		String[] ecsIdsArray = StringUtils.split(ecsIds, ",");
		String[] protocolsArray = StringUtils.split(protocols, ",");

		WSResult result = new WSResult();
		if (protocolsArray.length != ecsIdsArray.length) {
			result.setError(WSResult.PARAMETER_ERROR, "参数错误.");
			return result;
		}

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		List<ElbPolicyDTO> elbPolicyDTOs = new ArrayList<ElbPolicyDTO>();

		for (int i = 0; i < protocolsArray.length; i++) {

			EcsDTO ecsDTO = (EcsDTO) cmdbuildSoapService.findEcs(Integer.valueOf(ecsIdsArray[i])).getDto();

			if (ecsDTO == null) {
				result.setError(WSResult.PARAMETER_ERROR, "ECS不存在.");
				return result;
			}
			LookUpDTO lookUpDTO = findLookUpDTO(protocolsArray[i], "ELBProtocol");
			if (lookUpDTO == null) {
				result.setError(WSResult.PARAMETER_ERROR, "协议不存在.");
				return result;
			}
			ElbPolicyDTO policyDTO = new ElbPolicyDTO();
			policyDTO.setElbProtocol(lookUpDTO.getId());
			policyDTO.setSourcePort(getPortFromProtocol(lookUpDTO.getDescription()));
			policyDTO.setTargetPort(getPortFromProtocol(lookUpDTO.getDescription()));
			policyDTO.setIpaddress(ecsDTO.getId().toString());
			elbPolicyDTOs.add(policyDTO);
		}
		ElbDTO elbDTO = new ElbDTO();
		elbDTO.setAgentType(LookUpConstants.AgentType.Netscaler.getValue());
		elbDTO.setTenants(tenantsDTO.getId());
		return apiService.createELB(elbDTO, elbPolicyDTOs, ecsIds);
	}

	@Override
	public WSResult deleteELB(String code, String accessKey) {

		WSResult result = new WSResult();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		ElbDTO elbDTO = findElbDTO(tenantsDTO.getId(), code);
		if (elbDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "ELB不存在.");
			return result;
		}
		apiService.deleteELB(elbDTO.getId());
		result.setMessage("删除成功.");
		return result;
	}

	@Override
	public DTOResult<DnsEntity> findDNS(String code, String accessKey) {
		DTOResult<DnsEntity> result = new DTOResult<DnsEntity>();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		DnsDTO dnsDTO = findDnsDTO(tenantsDTO.getId(), code);
		if (dnsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "DNS不存在.");
			return result;
		}
		List<EipEntity> eipEntities = new ArrayList<EipEntity>();
		// 查询出DNS关联的EIP信息
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_idObj2", dnsDTO.getId());
		List<Object> list = cmdbuildSoapService.getMapEipDnsList(CMDBuildUtil.wrapperSearchParams(map)).getDtoList()
				.getDto();
		for (Object object : list) {
			MapEipDnsDTO mapEipDnsDTO = (MapEipDnsDTO) object;
			EipDTO eipDTO = (EipDTO) cmdbuildSoapService.findEip(Integer.valueOf(mapEipDnsDTO.getIdObj1())).getDto();
			EipEntity entity = new EipEntity(eipDTO.getCode(), eipDTO.getDescription(), eipDTO.getRemark(),
					eipDTO.getBandwidthText(), eipDTO.getIspText());
			eipEntities.add(entity);
		}
		DnsEntity entity = new DnsEntity(dnsDTO.getCode(), code, dnsDTO.getRemark(), eipEntities);
		result.setDto(entity);
		return result;
	}

	@Override
	public WSResult createDNS(String domainName, String eipIds, String protocols, String remark, String accessKey) {

		String[] eipIdsArray = StringUtils.split(eipIds, ",");
		String[] protocolsArray = StringUtils.split(protocols, ",");
		WSResult result = new WSResult();
		if (protocolsArray.length != eipIdsArray.length) {
			result.setError(WSResult.PARAMETER_ERROR, "参数错误.");
			return result;
		}
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		if (findDnsDTO(tenantsDTO.getId(), domainName) != null) {
			result.setError(WSResult.PARAMETER_ERROR, "域名已存在.");
			return result;
		}
		List<DnsPolicyDTO> dnsPolicyDTOs = new ArrayList<DnsPolicyDTO>();
		for (int i = 0; i < protocolsArray.length; i++) {

			EipDTO eipDTO = (EipDTO) cmdbuildSoapService.findEip(Integer.valueOf(eipIdsArray[i])).getDto();

			if (eipDTO == null) {
				result.setError(WSResult.PARAMETER_ERROR, "EIP不存在.");
				return result;
			}

			IpaddressDTO ipaddressDTO = (IpaddressDTO) cmdbuildSoapService.findIpaddress(eipDTO.getIpaddress())
					.getDto();

			LookUpDTO lookUpDTO = findLookUpDTO(protocolsArray[i], "DNSProtocol");
			if (lookUpDTO == null) {
				result.setError(WSResult.PARAMETER_ERROR, "协议不存在.");
				return result;
			}

			DnsPolicyDTO policyDTO = new DnsPolicyDTO();
			policyDTO.setDnsProtocol(lookUpDTO.getId());
			policyDTO.setPort(getPortFromProtocol(protocolsArray[i]).toString());
			policyDTO.setIpaddress(ipaddressDTO.getDescription());
			dnsPolicyDTOs.add(policyDTO);
		}
		DnsDTO dnsDTO = new DnsDTO();
		dnsDTO.setAgentType(LookUpConstants.AgentType.Netscaler.getValue());
		dnsDTO.setTenants(tenantsDTO.getId());
		dnsDTO.setDomainName(domainName);
		dnsDTO.setDescription(domainName);
		dnsDTO.setRemark(remark);
		apiService.createDNS(dnsDTO, dnsPolicyDTOs, eipIdsArray);
		return result;

	}

	@Override
	public WSResult deleteDNS(String code, String accessKey) {

		WSResult result = new WSResult();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}
		DnsDTO dnsDTO = findDnsDTO(tenantsDTO.getId(), code);
		apiService.deleteDNS(dnsDTO.getId());
		return result;
	}

	@Override
	public DTOResult<RouterEntity> findRouter(String code, String accessKey) {

		DTOResult<RouterEntity> result = new DTOResult<RouterEntity>();
		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		RouterDTO routerDTO = findRouterDTO(tenantsDTO.getId(), code);
		if (routerDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "Router不存在.");
			return result;
		}

		List<SubnetEntity> subnetEntities = new ArrayList<SubnetEntity>();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EQ_router", routerDTO.getId());

		List<Object> list = cmdbuildSoapService.getSubnetList(CMDBuildUtil.wrapperSearchParams(map)).getDtoList()
				.getDto();

		for (Object object : list) {
			SubnetDTO subnetDTO = (SubnetDTO) object;
			IdcDTO idcDTO = (IdcDTO) cmdbuildSoapService.findIdc(subnetDTO.getIdc()).getDto();
			SubnetEntity entity = new SubnetEntity(idcDTO.getDescription(), subnetDTO.getGateway(),
					subnetDTO.getCode(), subnetDTO.getNetMask(), subnetDTO.getRemark(), subnetDTO.getSegment(),
					subnetDTO.getDescription());
			subnetEntities.add(entity);
		}

		RouterEntity entity = new RouterEntity(routerDTO.getDescription(), subnetEntities);
		result.setDto(entity);
		return result;

	}

	@Override
	public WSResult createRouter(String routerName, String subnetCode, String remark, String routerSpec, String idc,
			String accessKey) {

		WSResult result = new WSResult();

		TenantsDTO tenantsDTO = findTenantsDTO(accessKey);
		if (tenantsDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "权限鉴证失败.");
			return result;
		}

		EcsSpecDTO ecsSpecDTO = findEcsSpecDTO(routerSpec);
		if (ecsSpecDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "规格不存在.");
			return result;
		}

		IdcDTO idcDTO = findIdcDTO(idc);
		if (idcDTO == null) {
			result.setError(WSResult.PARAMETER_ERROR, "IDC不存在.");
			return result;
		}

		SubnetDTO subnetDTO = findSubnetDTO(tenantsDTO.getId(), subnetCode);

		EcsDTO ecsDTO = new EcsDTO();
		ecsDTO.setAgentType(LookUpConstants.AgentType.Fortigate.getValue());
		ecsDTO.setDescription(routerName);
		ecsDTO.setEcsSpec(ecsSpecDTO.getId());
		ecsDTO.setIdc(idcDTO.getId());
		ecsDTO.setRemark(remark);
		ecsDTO.setTenants(tenantsDTO.getId());
		ecsDTO.setSubnet(subnetDTO.getId());

		result.setMessage(apiService.createECS(ecsDTO).getMessage());

		return result;
	}

	@Override
	public DTOResult<FirewallServiceEntity> findFirewallService(String code, String accessKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WSResult createFirewallService(FirewallServiceDTO firewallServiceDTO,
			List<ConfigFirewallServiceCategoryDTO> categoryDTOs, String accessKey) {
		// TODO Auto-generated method stub
		return null;
	}
}
