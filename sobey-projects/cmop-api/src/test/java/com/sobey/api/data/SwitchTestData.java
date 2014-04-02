package com.sobey.api.data;

import java.util.List;

import com.google.common.collect.Lists;
import com.sobey.generate.switches.ESGParameter;
import com.sobey.generate.switches.RuleParameter;
import com.sobey.generate.switches.VlanParameter;

public class SwitchTestData {

	public static VlanParameter randomVlanParameter() {

		VlanParameter parameter = new VlanParameter();

		parameter.setVlanId(80);
		parameter.setGateway("172.21.71.254");
		parameter.setNetMask("255.255.255.0");

		return parameter;
	}

	public static ESGParameter randomESGParameter() {

		ESGParameter parameter = new ESGParameter();

		parameter.setVlanId(80);
		parameter.setAclNumber(3000);
		parameter.setDesc("test ESG");

		List<RuleParameter> permits = Lists.newArrayList();

		RuleParameter rule1 = new RuleParameter();
		rule1.setSource("172.20.27.0");
		rule1.setSourceNetMask("0.0.0.255");
		rule1.setDestination("172.20.0.11");
		rule1.setDestinationNetMask("0.0.0.0");
		permits.add(rule1);

		RuleParameter rule2 = new RuleParameter();
		rule2.setSource("172.20.27.0");
		rule2.setSourceNetMask("0.0.0.255");
		rule2.setDestination("172.20.0.109");
		rule2.setDestinationNetMask("0.0.0.0");
		permits.add(rule2);

		parameter.getPermits().addAll(permits);

		List<RuleParameter> denys = Lists.newArrayList();

		RuleParameter rule3 = new RuleParameter();
		rule3.setSource("172.20.27.0");
		rule3.setSourceNetMask("0.0.0.255");
		rule3.setDestination("172.20.0.0");
		rule3.setDestinationNetMask("0.0.0.0");
		denys.add(rule3);

		RuleParameter rule4 = new RuleParameter();
		rule4.setSource("172.20.27.0");
		rule4.setSourceNetMask("0.0.0.255");
		rule4.setDestination("172.30.0.0");
		rule4.setDestinationNetMask("0.0.0.0");
		denys.add(rule4);

		parameter.getDenys().addAll(denys);

		return parameter;
	}

}
