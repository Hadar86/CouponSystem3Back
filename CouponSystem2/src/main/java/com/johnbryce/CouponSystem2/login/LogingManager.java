package com.johnbryce.CouponSystem2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.service.AdminService;
import com.johnbryce.CouponSystem2.service.ClientService;
import com.johnbryce.CouponSystem2.service.CompanyService;
import com.johnbryce.CouponSystem2.service.CustomerService;

@Component
public class LogingManager {
	@Autowired
	private ApplicationContext ctx;

	public ClientService login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case ADMINISTRATOR:
			ClientService adminService = ctx.getBean(AdminService.class);
			if (adminService.login(email, password)) {
				return adminService;
			}
			break;

		case COMPANY:
			ClientService companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(email, password)) {
				return companyService;
			}
			break;

		case CUSTOMER:
			ClientService customService = ctx.getBean(CustomerService.class);
			if (customService.login(email, password)) {
				return customService;
			}
			
			break;
		default:
			System.out.println("default case");
		}

		return null;

	}

}
