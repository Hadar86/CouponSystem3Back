package com.johnbryce.CouponSystem2.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.beans.Company;
import com.johnbryce.CouponSystem2.beans.Customer;
import com.johnbryce.CouponSystem2.exception.AllReadyExistException;
import com.johnbryce.CouponSystem2.exception.NoMatchException;
import com.johnbryce.CouponSystem2.exception.WrongException;
import com.johnbryce.CouponSystem2.print.PrintTable;
import com.johnbryce.CouponSystem2.repo.CompanyRepository;
import com.johnbryce.CouponSystem2.service.AdminService;

//@Component
//@Order(2)
public class AdminServiceTesting implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private AdminService adminService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("   _    ____  __  __ ___ _   _     ____  _____ ______     _____ ____ _____ \r\n"
				+ "    / \\  |  _ \\|  \\/  |_ _| \\ | |   / ___|| ____|  _ \\ \\   / /_ _/ ___| ____|\r\n"
				+ "   / _ \\ | | | | |\\/| || ||  \\| |   \\___ \\|  _| | |_) \\ \\ / / | | |   |  _|  \r\n"
				+ "  / ___ \\| |_| | |  | || || |\\  |    ___) | |___|  _ < \\ V /  | | |___| |___ \r\n"
				+ " /_/   \\_\\____/|_|  |_|___|_| \\_|___|____/|_____|_| \\_\\ \\_/  |___\\____|_____|\r\n"
				+ "                               |_____|                                       ");

		System.out.println("********************************LOGIN_COMPANY**************************************");
		boolean result = adminService.login("admin@admin.com", "admin");
		if (result) {
			System.out.println("WELCOME");

		} else {
			System.out.println("SORRY");
		}

		System.out.println(
				"********************************LOGIN_COMPANY NOT WORKING**************************************");
		boolean result2 = adminService.login("admin@111.com", "admin");
		if (result2) {
			System.out.println("WELCOME");

		} else {
			System.out.println("SORRY");
		}
		System.out.println("********************************ADD_COMPANY**************************************");
		Company p4 = new Company("HAVAIANAS", "havaianas@havaianas.com", "7777", null);

		try {
			adminService.addCompany(p4);

		} catch (AllReadyExistException e) {
			System.out.println(e.getMessage());

		}
		PrintTable.print(adminService.getAllCompanies());

		System.out.println(
				"********************************ADD_COMPANY NOT WORKING**************************************");
		Company p5 = new Company("PLAYTIKA", "havaianas@havaianas.com", "7777", null);

		try {
			adminService.addCompany(p5);

		} catch (AllReadyExistException e) {
			System.out.println(e.getMessage());
		}
		PrintTable.print(adminService.getAllCompanies());

		System.out.println("********************************UPDATE_COMPANY**************************************");
		p4.setEmail("aaa@aaa.com");
		try {
			adminService.updateCompany(p4);
		} catch (NoMatchException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println();
		PrintTable.print(adminService.getAllCompanies());

		System.out.println(
				"********************************UPDATE_COMPANY NOT WORKING**********************************");
		p4.setId(10);

		try {
			adminService.updateCompany(p4);
		} catch (NoMatchException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println();
		PrintTable.print(adminService.getAllCompanies());

		System.out.println("********************************DELETE_COMPANY**************************************");
		try {

			adminService.deleteCompany(4);

			// I'm going to delete company(2) in customerServisTesting to see if it will
			// delete the same coupon that is related to company and customer.
			// adminService.deleteCompany(2);

			PrintTable.print(adminService.getAllCompanies());

		} catch (NoMatchException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("********************************GET_ALL_COMPANIES**************************************");
		PrintTable.print(adminService.getAllCompanies());

		System.out.println("********************************GET_ONE_COMPANY************************************");
		PrintTable.print(adminService.getOneCompany(1));

		System.out.println("********************************ADD_CUSTOMER******************************************");
		Customer t4 = new Customer("Dean", "Klainshtain", "dean@dean.com", "4321", null);

		try {
			adminService.addCustomer(t4);

			PrintTable.print(adminService.getAllCustomers());
		} catch (AllReadyExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(
				"********************************ADD_CUSTOMER NOT WORKING******************************************");
		Customer t5 = new Customer("Ben", "Klainshtain", "dean@dean.com", "4321", null);

		try {
			adminService.addCustomer(t5);

			PrintTable.print(adminService.getAllCustomers());
		} catch (AllReadyExistException e) {
			System.out.println(e.getMessage());
			PrintTable.print(adminService.getAllCustomers());

		}
		System.out.println("********************************UPDATE_CUSTOMER**************************************");

		t4.setLastName("Golan");
		try {
			adminService.updateCustomer(t4);

		} catch (NoMatchException | WrongException e) {

			System.out.println(e.getMessage());
		}
		PrintTable.print(adminService.getAllCustomers());

		System.out.println(
				"********************************UPDATE_CUSTOMER NOT WORKING**************************************");
		t4.setId(90);

		try {
			adminService.updateCustomer(t4);

		} catch (NoMatchException | WrongException e) {

			System.out.println(e.getMessage());
		}

		System.out.println("********************************DELETE_CUSTOMER**************************************");
		adminService.deleteCustomer(4);
		PrintTable.print(adminService.getAllCustomers());

		System.out.println("********************************GET_ALL_CUSTOMERS**************************************");
		PrintTable.print(adminService.getAllCustomers());

		System.out.println("********************************GET_ONE_CUSTOMERS**************************************");
		PrintTable.print(adminService.getOneCustomer(2));

	}
}
