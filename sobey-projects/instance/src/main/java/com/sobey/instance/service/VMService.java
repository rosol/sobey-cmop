package com.sobey.instance.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sobey.core.utils.PropertiesLoader;
import com.sobey.instance.constans.DataCenterEnum;
import com.sobey.instance.webservice.response.dto.CloneVMParameter;
import com.sobey.instance.webservice.response.dto.DestroyVMParameter;
import com.sobey.instance.webservice.response.dto.PowerVMParameter;
import com.sobey.instance.webservice.response.dto.ReconfigVMParameter;
import com.sobey.instance.webservice.response.dto.RelationVMParameter;
import com.vmware.vim25.CustomizationAdapterMapping;
import com.vmware.vim25.CustomizationFixedIp;
import com.vmware.vim25.CustomizationFixedName;
import com.vmware.vim25.CustomizationGlobalIPSettings;
import com.vmware.vim25.CustomizationGuiUnattended;
import com.vmware.vim25.CustomizationIPSettings;
import com.vmware.vim25.CustomizationIdentification;
import com.vmware.vim25.CustomizationLicenseDataMode;
import com.vmware.vim25.CustomizationLicenseFilePrintData;
import com.vmware.vim25.CustomizationLinuxOptions;
import com.vmware.vim25.CustomizationLinuxPrep;
import com.vmware.vim25.CustomizationPassword;
import com.vmware.vim25.CustomizationSpec;
import com.vmware.vim25.CustomizationSpecInfo;
import com.vmware.vim25.CustomizationSpecItem;
import com.vmware.vim25.CustomizationSysprep;
import com.vmware.vim25.CustomizationSysprepRebootOption;
import com.vmware.vim25.CustomizationUserData;
import com.vmware.vim25.CustomizationWinOptions;
import com.vmware.vim25.DVPortgroupConfigSpec;
import com.vmware.vim25.DistributedVirtualSwitchPortConnection;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.TaskInfoState;
import com.vmware.vim25.VMwareDVSPortSetting;
import com.vmware.vim25.VirtualDevice;
import com.vmware.vim25.VirtualDeviceConfigSpec;
import com.vmware.vim25.VirtualDeviceConfigSpecOperation;
import com.vmware.vim25.VirtualDeviceConnectInfo;
import com.vmware.vim25.VirtualEthernetCard;
import com.vmware.vim25.VirtualEthernetCardDistributedVirtualPortBackingInfo;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.VirtualMachineConfigSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.VirtualVmxnet3;
import com.vmware.vim25.VmwareDistributedVirtualSwitchVlanIdSpec;
import com.vmware.vim25.mo.CustomizationSpecManager;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.DistributedVirtualPortgroup;
import com.vmware.vim25.mo.DistributedVirtualSwitch;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.Network;
import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

@Service
public class VMService {

	private static Logger logger = LoggerFactory.getLogger(VMService.class);

	/**
	 * 加载applicationContext.propertie文件
	 */
	private static PropertiesLoader INSTANCE_LOADER = new PropertiesLoader("classpath:/instance.properties");

	/* 脚本参数 */

	/**
	 * windows模板默认密码.
	 */
	private static final String WINDOWS_DEFAULT_PASSWORD = INSTANCE_LOADER.getProperty("WINDOWS_DEFAULT_PASSWORD");

	/********** 成都 ***********/
	/**
	 * IP
	 */
	private static final String INSTANCE_IP_CD = INSTANCE_LOADER.getProperty("INSTANCE_IP_CD");

	/**
	 * Username
	 */
	private static final String INSTANCE_USERNAME_CD = INSTANCE_LOADER.getProperty("INSTANCE_USERNAME_CD");

	/**
	 * password
	 */
	private static final String INSTANCE_PASSWORD_CD = INSTANCE_LOADER.getProperty("INSTANCE_PASSWORD_CD");

	/********** 西安 ***********/
	/**
	 * IP
	 */
	private static final String INSTANCE_IP_XA = INSTANCE_LOADER.getProperty("INSTANCE_IP_XA");

	/**
	 * Username
	 */
	private static final String INSTANCE_USERNAME_XA = INSTANCE_LOADER.getProperty("INSTANCE_USERNAME_XA");

	/**
	 * password
	 */
	private static final String INSTANCE_PASSWORD_XA = INSTANCE_LOADER.getProperty("INSTANCE_PASSWORD_XA");

	/**
	 * 网卡名
	 */
	private static final String INSTANCE_NIC_NAME = INSTANCE_LOADER.getProperty("INSTANCE_NIC_NAME");

	/**
	 * 分布式交换机名
	 */
	private static final String INSTANCE_DVS_NAME = INSTANCE_LOADER.getProperty("INSTANCE_DVS_NAME");

	public boolean addDVSPortGroup(Integer vlanId, String datacenter) {

		boolean flag = false;

		// 先判断vcneter中是否有该端口组.如果有则返回false.
		if (getNetworkList(datacenter).contains(getPortGroupName(vlanId))) {
			return flag;
		}

		try {

			ServiceInstance si = getServiceInstance(datacenter);

			DistributedVirtualSwitch dvs = (DistributedVirtualSwitch) new InventoryNavigator(si.getRootFolder())
					.searchManagedEntity("DistributedVirtualSwitch", INSTANCE_DVS_NAME);

			if (dvs == null) {
				return flag;
			}

			// create port group under this DVS
			DVPortgroupConfigSpec[] dvpgs = new DVPortgroupConfigSpec[1];
			dvpgs[0] = new DVPortgroupConfigSpec();
			dvpgs[0].setName(getPortGroupName(vlanId));
			dvpgs[0].setNumPorts(128);
			dvpgs[0].setType("earlyBinding");
			VMwareDVSPortSetting vport = new VMwareDVSPortSetting();
			dvpgs[0].setDefaultPortConfig(vport);

			VmwareDistributedVirtualSwitchVlanIdSpec vlan = new VmwareDistributedVirtualSwitchVlanIdSpec();
			vport.setVlan(vlan);
			vlan.setInherited(false);
			vlan.setVlanId(vlanId);

			Task task_pg = dvs.addDVPortgroup_Task(dvpgs);

			TaskInfo ti = waitFor(task_pg);

			if (ti.getState() == TaskInfoState.error) {
				logger.info("Failed to create a new DVS. Vlan Id is:" + vlanId);
				return flag;
			}

		} catch (RemoteException e) {
			logger.info("RemoteException::addDVSPortGroup::" + e);
		} catch (MalformedURLException e) {
			logger.info("MalformedURLException::addDVSPortGroup::" + e);
		} catch (InterruptedException e) {
			logger.info("InterruptedException::addDVSPortGroup::" + e);
		}

		return true;

	}

	private static TaskInfo waitFor(Task task) throws RemoteException, InterruptedException {
		while (true) {
			TaskInfo ti = task.getTaskInfo();
			TaskInfoState state = ti.getState();
			if (state == TaskInfoState.success || state == TaskInfoState.error) {
				return ti;
			}
			Thread.sleep(1000);
		}
	}

	/**
	 * 所有的网络设备名称的集合
	 * 
	 * @param datacenter
	 *            数据中心
	 * @return
	 */
	private List<String> getNetworkList(String datacenter) {

		List<String> list = new ArrayList<String>();
		Datacenter dc = null;
		List<Network> networks = new ArrayList<Network>();

		Folder rootFolder;
		try {

			rootFolder = getServiceInstance(datacenter).getRootFolder();

			ManagedEntity[] datacenters = rootFolder.getChildEntity();

			// 获得数据中心
			for (int i = 0; i < datacenters.length; i++) {
				if (datacenters[i] instanceof Datacenter) {
					dc = (Datacenter) datacenters[i];
					break;
				}
			}

			// 获得网络
			for (ManagedEntity entity : dc.getNetworkFolder().getChildEntity()) {
				if (entity instanceof Network) {
					networks.add(((Network) entity));
				}
			}

			for (Network each : networks) {
				list.add(each.getName());
			}

		} catch (RemoteException | MalformedURLException e) {
			logger.info("Exception::getNetworkList::" + e);
		}

		return list;
	}

	/**
	 * 
	 * 
	 * @param vmName
	 *            虚拟机名
	 * @param portGroupName
	 *            分布式端口组名
	 * @param nicName
	 *            网卡名(一般是默认的,名字由配置文件中读取)
	 * @return
	 */

	/**
	 * 为虚拟机分配分布式端口组
	 * 
	 * @param datacenter
	 *            数据中心
	 * @param vmName
	 *            虚拟机名
	 * @param vlanId
	 *            vlan Id
	 * @return
	 */
	public boolean changeVlan(String datacenter, String vmName, Integer vlanId) {

		boolean retVal = false;

		String portGroupName = getPortGroupName(vlanId);

		try {
			ServiceInstance si = getServiceInstance(datacenter);

			VirtualMachine vm = getVirtualMachine(si, vmName);

			Folder rootFolder = si.getRootFolder();

			Network network = (Network) new InventoryNavigator(rootFolder)
					.searchManagedEntity("Network", portGroupName);

			if (network == null) {
				logger.info("Error::changeVlan::Could not find network," + portGroupName + " 不存在");
				return retVal;
			}

			ManagedEntity[] entity = new InventoryNavigator(rootFolder)
					.searchManagedEntities("DistributedVirtualSwitch");

			DistributedVirtualSwitch dvs = null;
			String key = "";
			boolean found = false;

			for (ManagedEntity me : entity) {

				if (me instanceof DistributedVirtualSwitch) {

					DistributedVirtualSwitch tmpDvs = (DistributedVirtualSwitch) me;
					DistributedVirtualPortgroup[] vpgs = tmpDvs.getPortgroup();

					for (DistributedVirtualPortgroup vpg : vpgs) {
						// 查找是否有分布式端口组.
						if (portGroupName.equals(vpg.getName())) {
							key = vpg.getConfig().getKey();
							dvs = tmpDvs;
							found = true;
							break;
						}
					}

					if (found) {
						break;
					}
				}
			}

			VirtualMachineConfigSpec vmSpec = new VirtualMachineConfigSpec();

			VirtualMachineConfigInfo vmConfigInfo = vm.getConfig();

			String uuid = dvs.getConfig().getUuid();

			ArrayList<VirtualDeviceConfigSpec> nicSpecList = new ArrayList<VirtualDeviceConfigSpec>();

			VirtualDevice[] vds = vmConfigInfo.getHardware().getDevice();
			for (VirtualDevice vd : vds) {
				if (vd instanceof VirtualEthernetCard) {

					VirtualDeviceConfigSpec nicSpec = new VirtualDeviceConfigSpec();
					nicSpec.setOperation(VirtualDeviceConfigSpecOperation.edit);

					VirtualEthernetCard nic = (VirtualEthernetCard) vd;

					if (nic.getDeviceInfo().getLabel().equalsIgnoreCase(INSTANCE_NIC_NAME)) {

						VirtualEthernetCard newNic = new VirtualVmxnet3();
						newNic.setKey(nic.getKey());
						newNic.setDeviceInfo(nic.getDeviceInfo());

						newNic.getDeviceInfo().setLabel(INSTANCE_NIC_NAME);

						VirtualEthernetCardDistributedVirtualPortBackingInfo backing9 = new VirtualEthernetCardDistributedVirtualPortBackingInfo();

						DistributedVirtualSwitchPortConnection port10 = new DistributedVirtualSwitchPortConnection();
						port10.setSwitchUuid(uuid);
						port10.setPortgroupKey(key);
						backing9.setPort(port10);

						newNic.setBacking(backing9);
						newNic.setAddressType("assigned");
						newNic.setMacAddress(nic.getMacAddress());
						newNic.setControllerKey(nic.getControllerKey());
						newNic.setUnitNumber(nic.getUnitNumber());

						VirtualDeviceConnectInfo connectable11 = new VirtualDeviceConnectInfo();
						connectable11.startConnected = true;
						connectable11.allowGuestControl = true;
						connectable11.connected = true;
						connectable11.status = "untried";

						newNic.setConnectable(connectable11);

						System.out.println("Setting UUID: " + uuid);
						System.out.println("Setting portgroupKey: " + key);

						nicSpec.setDevice(newNic);

						nicSpecList.add(nicSpec);

					}

				}
			}

			VirtualDeviceConfigSpec[] configSpec = new VirtualDeviceConfigSpec[nicSpecList.size()];
			nicSpecList.toArray(configSpec);

			vmSpec.setDeviceChange(configSpec);

			Task vmTask = vm.reconfigVM_Task(vmSpec);

			String result = vmTask.waitForTask();

			retVal = result.equals(Task.SUCCESS);

		} catch (Exception e) {
			logger.info("Exception::changeVlan::" + e);
		}

		return retVal;
	}

	public boolean cloneVM(CloneVMParameter parameter) {

		try {

			ServiceInstance si = getServiceInstance(parameter.getDatacenter());

			VirtualMachine vm = getVirtualMachine(si, parameter.getvMTemplateName());

			if (vm == null) {
				logout(si);
				return false;
			}

			// 虚拟机克隆方案创建
			VirtualMachineCloneSpec cloneSpec = new VirtualMachineCloneSpec();

			// CustomizationSpec数据对象类型包含需要自定义虚拟机部署时或将其迁移到新的主机的信息。
			CustomizationSpec cspec = new CustomizationSpec();
			CustomizationSpecInfo info = new CustomizationSpecInfo();
			CustomizationSpecItem specItem = new CustomizationSpecItem();

			CustomizationAdapterMapping adaptorMap = new CustomizationAdapterMapping();
			CustomizationIPSettings adapter = new CustomizationIPSettings();
			CustomizationFixedIp fixedIp = new CustomizationFixedIp();// 指定使用固定ip
			CustomizationGlobalIPSettings gIP = new CustomizationGlobalIPSettings();

			info.setDescription(parameter.getvMTemplateOS());
			info.setName("Sobey");
			info.setType(parameter.getvMTemplateOS());// 设置克隆机器的操作系统类型

			specItem.setInfo(info);
			specItem.setSpec(cspec);

			// dns列表
			String dnsList[] = new String[] { "8.8.8.8" };
			String ipAddress = parameter.getIpaddress(); // 自定义的内网IP
			String subNetMask = parameter.getSubNetMask();

			adapter.setDnsServerList(dnsList);
			adapter.setGateway(new String[] { parameter.getGateway() });
			adapter.setIp(fixedIp);
			adapter.setSubnetMask(subNetMask);

			fixedIp.setIpAddress(ipAddress);
			adaptorMap.setAdapter(adapter);

			// 不能使用MAC设置
			String dnsSuffixList[] = new String[] { "sobey.com", "sobey.cn" };
			gIP.setDnsSuffixList(dnsSuffixList);
			gIP.setDnsServerList(dnsList);

			CustomizationFixedName computerName = new CustomizationFixedName();
			computerName.setName("cmop");// 无法确认VM用户名是否能为中文,目前暂定为所有都是cmop

			CustomizationAdapterMapping[] nicSettingMap = new CustomizationAdapterMapping[] { adaptorMap };

			CustomizationSpecManager specManager = si.getCustomizationSpecManager();

			if ("Linux".equals(parameter.getvMTemplateOS())) {

				CustomizationLinuxOptions linuxOptions = new CustomizationLinuxOptions();
				CustomizationLinuxPrep cLinuxPrep = new CustomizationLinuxPrep();
				cLinuxPrep.setDomain("sobeyc.com");
				cLinuxPrep.setHostName(computerName);
				cLinuxPrep.setHwClockUTC(true);
				cLinuxPrep.setTimeZone("Asia/Shanghai");

				cspec.setOptions(linuxOptions);
				cspec.setIdentity(cLinuxPrep);

			} else if ("Windows".equals(parameter.getvMTemplateOS())) {

				CustomizationWinOptions winOptions = new CustomizationWinOptions();
				CustomizationSysprep cWinSysprep = new CustomizationSysprep();

				CustomizationGuiUnattended guiUnattended = new CustomizationGuiUnattended();
				guiUnattended.setAutoLogon(false);
				guiUnattended.setAutoLogonCount(1);
				guiUnattended.setTimeZone(210); // http://msdn.microsoft.com/en-us/library/ms912391%28v=winembedded.11%29.aspx

				CustomizationPassword password = new CustomizationPassword();
				password.setValue(WINDOWS_DEFAULT_PASSWORD);
				password.setPlainText(true);
				guiUnattended.setPassword(password);
				cWinSysprep.setGuiUnattended(guiUnattended);

				CustomizationUserData userData = new CustomizationUserData();
				userData.setProductId("");
				userData.setFullName("Sobey");
				userData.setOrgName("Sobey");
				userData.setComputerName(computerName);
				cWinSysprep.setUserData(userData);

				// Windows Server 2000, 2003 必须
				CustomizationLicenseFilePrintData printData = new CustomizationLicenseFilePrintData();
				printData.setAutoMode(CustomizationLicenseDataMode.perSeat);
				cWinSysprep.setLicenseFilePrintData(printData);

				CustomizationIdentification identification = new CustomizationIdentification();
				identification.setJoinWorkgroup("Sobey");
				cWinSysprep.setIdentification(identification);

				winOptions.setReboot(CustomizationSysprepRebootOption.shutdown);
				winOptions.setChangeSID(true);
				winOptions.setDeleteAccounts(false);

				cspec.setOptions(winOptions);
				cspec.setIdentity(cWinSysprep);

			} else {
				logger.info("cloneVM::OS类型不正确");
				return false;
			}

			cspec.setGlobalIPSettings(gIP);
			cspec.setNicSettingMap(nicSettingMap);
			cspec.setEncryptionKey(specManager.getEncryptionKey());

			// 设置ResourcePool
			/**
			 * TODO 重要:宿主机暂时写死,宿主机的Value可以在VMTest中的PrintInventory方法查出来.
			 * 
			 * 后期应该做到CMDBuild查询宿主机的负载能力,找出负载最低的宿主机, 并根据名称查出ManagedObjectReference对象的value.
			 */
			ManagedObjectReference pool = new ManagedObjectReference();
			pool.set_value("resgroup-8");
			pool.setType("ResourcePool");
			pool.setVal("resgroup-8");

			VirtualMachineRelocateSpec relocateSpec = new VirtualMachineRelocateSpec();
			relocateSpec.setPool(pool);
			cloneSpec.setLocation(relocateSpec);
			cloneSpec.setPowerOn(true);
			cloneSpec.setTemplate(false);
			cloneSpec.setCustomization(cspec);

			vm.checkCustomizationSpec(specItem.getSpec());

			Task task = vm.cloneVM_Task((Folder) vm.getParent(), parameter.getvMName(), cloneSpec);

			if (task.waitForTask() != Task.SUCCESS) {
				logger.info("Failure -: VM cannot be cloned");
				return false;
			}

			// 为虚拟机设置网络适配器相关信息:设备状态设置为已连接、网络标签、虚拟机的备注.

		} catch (InvalidProperty e) {
			logger.info("cloneVM::InvalidProperty:" + e);
			return false;
		} catch (RuntimeFault e) {
			logger.info("cloneVM::RuntimeFault:" + e);
			return false;
		} catch (RemoteException e) {
			logger.info("cloneVM::RemoteException:" + e);
			return false;
		} catch (MalformedURLException e) {
			logger.info("cloneVM::MalformedURLException:" + e);
			return false;
		} catch (InterruptedException e) {
			logger.info("cloneVM::InterruptedException:" + e);
			return false;
		}

		return true;
	}

	/**
	 * 返回vcneter中分布式端口组名.
	 * 
	 * 分布式端口组名是按 vlan+Id 的格式组成
	 * 
	 * @param vlanId
	 * @return
	 */
	private static String getPortGroupName(Integer vlanId) {
		return "vlan" + vlanId;
	}

	/**
	 * 销毁一个虚拟机
	 * 
	 * @param parameter
	 *            {@link DestroyVMParameter}
	 * @return
	 */
	public boolean destroyVM(DestroyVMParameter parameter) {

		try {

			ServiceInstance si = getServiceInstance(parameter.getDatacenter());

			VirtualMachine vm = (VirtualMachine) new InventoryNavigator(si.getRootFolder()).searchManagedEntity(
					"VirtualMachine", parameter.getvMName());

			if (vm == null) {
				logout(si);
				return false;
			}

			vm.destroy_Task();

		} catch (RemoteException e) {
			logger.info("destroyVM::RemoteException:" + e);
			return false;
		} catch (MalformedURLException e) {
			logger.info("destroyVM::MalformedURLException:" + e);
			return false;
		}

		return true;
	}

	public boolean reconfigVM(ReconfigVMParameter parameter) {

		try {

			ServiceInstance si = getServiceInstance(parameter.getDatacenter());
			VirtualMachine vm = getVirtualMachine(si, parameter.getvMName());

			VirtualMachineConfigSpec vmConfigSpec = new VirtualMachineConfigSpec();

			vmConfigSpec.setMemoryMB(parameter.getMemoryMB()); // in MB
			vmConfigSpec.setNumCPUs(parameter.getcPUNumber());

			Task task = vm.reconfigVM_Task(vmConfigSpec);

			if (task.waitForTask() != Task.SUCCESS) {
				logger.info("Failure -: VM cannot be cloned");
				return false;
			}

		} catch (RemoteException e) {
			logger.info("reconfigVM::RemoteException:" + e);
			return false;
		} catch (MalformedURLException e) {
			logger.info("reconfigVM::MalformedURLException:" + e);
			return false;
		} catch (InterruptedException e) {
			logger.info("reconfigVM::InterruptedException:" + e);
			return false;
		}

		return true;

	}

	public boolean powerVM(PowerVMParameter parameter) {

		try {

			ServiceInstance si = getServiceInstance(parameter.getDatacenter());
			VirtualMachine vm = getVirtualMachine(si, parameter.getvMName());

			if (vm == null) {
				logout(si);
				return false;
			}

			if ("reboot".equalsIgnoreCase(parameter.getPowerOperation())) {
				vm.rebootGuest();
			} else if ("poweron".equalsIgnoreCase(parameter.getPowerOperation())) {

				Task task = vm.powerOnVM_Task(null);
				if (task.waitForTask() != Task.SUCCESS) {
					return false;
				}

			} else if ("poweroff".equalsIgnoreCase(parameter.getPowerOperation())) {

				Task task = vm.powerOffVM_Task();
				if (task.waitForTask() != Task.SUCCESS) {
					return false;
				}

			} else if ("reset".equalsIgnoreCase(parameter.getPowerOperation())) {

				Task task = vm.resetVM_Task();
				if (task.waitForTask() != Task.SUCCESS) {
					return false;
				}

			} else if ("standby".equalsIgnoreCase(parameter.getPowerOperation())) {

				vm.standbyGuest();

			} else if ("suspend".equalsIgnoreCase(parameter.getPowerOperation())) {

				Task task = vm.suspendVM_Task();
				if (task.waitForTask() != Task.SUCCESS) {
					return false;
				}

			} else if ("shutdown".equalsIgnoreCase(parameter.getPowerOperation())) {

				Task task = vm.suspendVM_Task();
				if (task.waitForTask() != Task.SUCCESS) {
					return false;
				}

			} else {
				return false;
			}

			logout(si);

		} catch (RemoteException e) {
			logger.info("powerVM::RemoteException:" + e);
			return false;
		} catch (MalformedURLException e) {
			logger.info("powerVM::MalformedURLException:" + e);
			return false;
		} catch (InterruptedException e) {
			logger.info("powerVM::InterruptedException:" + e);
			return false;
		}

		return true;

	}

	/**
	 * 通过HashMap获得Host和VM的关联关系.<br>
	 * 
	 * 利用HashMap key不能重复,value可以重复的特性,将VM名保存在key中,而Host名因为有重复的存在,所以放在value.<br>
	 * 这样保证了每个key(VM)对应一个Value(Host),key永远不会重复
	 * 
	 * @return
	 */
	public RelationVMParameter getVMAndHostRelation(String datacenter) {

		HashMap<String, String> map = Maps.newHashMap();

		try {

			ServiceInstance si = getServiceInstance(datacenter);

			ManagedEntity[] mes = new InventoryNavigator(si.getRootFolder()).searchManagedEntities("VirtualMachine");
			if (mes == null || mes.length == 0) {
				return null;
			}

			for (int i = 0; i < mes.length; i++) {

				VirtualMachine vm = (VirtualMachine) mes[i];

				ResourcePool pool = vm.getResourcePool();

				if (pool != null) {
					map.put(vm.getName(), pool.getParent().getName());
				}

			}

		} catch (RemoteException e) {
			logger.info("RelationVMParameter::RemoteException:" + e);
		} catch (MalformedURLException e) {
			logger.info("RelationVMParameter::MalformedURLException:" + e);
		}

		RelationVMParameter parameter = new RelationVMParameter();
		parameter.setRelationMaps(map);
		return parameter;
	}

	/**
	 * 初始化一个服务实例
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	private ServiceInstance getServiceInstance(String datacenter) throws RemoteException, MalformedURLException {

		if (DataCenterEnum.CD.toString().equalsIgnoreCase(datacenter)) {
			return new ServiceInstance(new URL(INSTANCE_IP_CD), INSTANCE_USERNAME_CD, INSTANCE_PASSWORD_CD, true);
		} else if (DataCenterEnum.XA.toString().equalsIgnoreCase(datacenter)) {
			return new ServiceInstance(new URL(INSTANCE_IP_XA), INSTANCE_USERNAME_XA, INSTANCE_PASSWORD_XA, true);
		}

		return null;
	}

	/**
	 * 根据虚拟机名获得虚拟机对象
	 * 
	 * @param si
	 *            服务实例
	 * @param vmname
	 *            虚拟机名
	 * @return
	 * @throws InvalidProperty
	 * @throws RuntimeFault
	 * @throws RemoteException
	 */
	private VirtualMachine getVirtualMachine(ServiceInstance si, String vmname) throws InvalidProperty, RuntimeFault,
			RemoteException {
		return (VirtualMachine) new InventoryNavigator(si.getRootFolder())
				.searchManagedEntity("VirtualMachine", vmname);

	}

	/**
	 * 退出服务实例
	 * 
	 * @param si
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	private void logout(ServiceInstance si) throws RemoteException, MalformedURLException {
		si.getServerConnection().logout();
	}

}
