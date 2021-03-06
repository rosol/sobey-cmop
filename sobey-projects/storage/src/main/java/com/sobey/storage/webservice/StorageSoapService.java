package com.sobey.storage.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.sobey.storage.constans.WsConstants;
import com.sobey.storage.webservice.response.dto.CreateEs3Parameter;
import com.sobey.storage.webservice.response.dto.DeleteEs3Parameter;
import com.sobey.storage.webservice.response.dto.ModifytEs3RuleParameter;
import com.sobey.storage.webservice.response.dto.NetAppParameter;
import com.sobey.storage.webservice.response.dto.VolumeInfoDTO;
import com.sobey.storage.webservice.response.result.DTOListResult;
import com.sobey.storage.webservice.response.result.DTOResult;
import com.sobey.storage.webservice.response.result.WSResult;

/**
 * netapp对外暴露的唯一的webservice接口.
 * 
 * @author Administrator
 * 
 */
@WebService(name = "StorageSoapService", targetNamespace = WsConstants.NS)
public interface StorageSoapService {

	/**
	 * 创建Volume
	 * 
	 * @param createEs3Parameter
	 *            {@link CreateEs3Parameter}
	 * @return
	 */
	WSResult createEs3ByStorage(@WebParam(name = "createEs3Parameter") CreateEs3Parameter createEs3Parameter);

	/**
	 * 删除Volume
	 * 
	 * @param deleteEs3Parameter
	 *            {@link DeleteEs3Parameter}
	 * @return
	 */
	WSResult deleteEs3ByStorage(@WebParam(name = "deleteEs3Parameter") DeleteEs3Parameter deleteEs3Parameter);

	/**
	 * 修改netapp卷的Client Permissions,即允许哪些IP可以挂载卷.
	 * 
	 * @param modifytEs3RuleParameter
	 *            {@link ModifytEs3RuleParameter}
	 * @return
	 */
	WSResult modifytEs3RuleParameterByStorage(
			@WebParam(name = "modifytEs3RuleParameter") ModifytEs3RuleParameter modifytEs3RuleParameter);

	/**
	 * 获得controller下所有的卷
	 * 
	 * @param netAppParameter
	 *            {@link NetAppParameter}
	 * @return
	 */
	DTOListResult<VolumeInfoDTO> getVolumeInfoDTO(@WebParam(name = "netAppParameter") NetAppParameter netAppParameter);

	/**
	 * 根据卷名获得卷信息
	 * 
	 * @param volumeName
	 *            卷名
	 * @return
	 */
	DTOResult<VolumeInfoDTO> findVolumeInfoDTO(@WebParam(name = "netAppParameter") NetAppParameter netAppParameter);
}
