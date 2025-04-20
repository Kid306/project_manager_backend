package com.mdp.safe.client.service;

import com.mdp.safe.client.entity.Qx;
import com.mdp.safe.client.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GuestService {

	@Autowired
    UserResourceQueryService userClientService;

	String GUEST_ROLE="guest";
	
	List<Role> guestRoles=new ArrayList<>();


	@Scheduled(initialDelay=60000,fixedDelay=600000)
	public void init(){
		guestRoles=new ArrayList<>();
		Role role=new Role();
		role.setRoleid(GUEST_ROLE);
		role.setRolename("游客");
		guestRoles.add(role);
		//getRoleResources();
	}
	
	public Map<String, Qx> getRoleQxs(){
			return userClientService.loadRoleQxsByRoleids(GUEST_ROLE);
	}


	
}
