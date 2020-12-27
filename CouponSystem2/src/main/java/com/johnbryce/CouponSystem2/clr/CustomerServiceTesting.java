package com.johnbryce.CouponSystem2.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.beans.Category;

import com.johnbryce.CouponSystem2.exception.AllReadyExistException;
import com.johnbryce.CouponSystem2.exception.WrongException;
import com.johnbryce.CouponSystem2.print.PrintTable;
import com.johnbryce.CouponSystem2.repo.CouponRepository;
import com.johnbryce.CouponSystem2.service.AdminService;
import com.johnbryce.CouponSystem2.service.CompanyService;
import com.johnbryce.CouponSystem2.service.CustomerService;

//@Component
//@Order(4)
public class CustomerServiceTesting implements CommandLineRunner {

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdminService adminService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(
				"  ____ _   _ ____ _____ ___  __  __ _____ ____      ____  _____ ______     _____ ____ _____ \r\n"
						+ "  / ___| | | / ___|_   _/ _ \\|  \\/  | ____|  _ \\    / ___|| ____|  _ \\ \\   / /_ _/ ___| ____|\r\n"
						+ " | |   | | | \\___ \\ | || | | | |\\/| |  _| | |_) |   \\___ \\|  _| | |_) \\ \\ / / | | |   |  _|  \r\n"
						+ " | |___| |_| |___) || || |_| | |  | | |___|  _ <     ___) | |___|  _ < \\ V /  | | |___| |___ \r\n"
						+ "  \\____|\\___/|____/ |_| \\___/|_|  |_|_____|_| \\_\\___|____/|_____|_| \\_\\ \\_/  |___\\____|_____|\r\n"
						+ "                                               |_____|                                       ");

		System.out.println("********************************LOGIN_CUSTOMER*****************************************");
		System.out.println(customerService.login("kobi@gmail.com", "Bb@12345"));

		System.out.println(
				"********************************LOGIN_CUSTOMER NOT WORKING*****************************************");
		System.out.println(customerService.login("kobi@asdf.com", "1234"));

		System.out.println("********************************PURCHASE_COUPON*****************************************");

		customerService.purchaseCoupon(couponRepository.getOne(1));
		customerService.purchaseCoupon(couponRepository.getOne(4));
		//customerService.purchaseCoupon(couponRepository.getOne(2));

		PrintTable.print(customerService.getCustomerCoupons());
		System.out.println(

				"********************************PURCHASE_COUPON NOT WORKING*****************************************");
		try {
			customerService.purchaseCoupon(couponRepository.getOne(3));

		} catch (WrongException | AllReadyExistException e) {
			System.out.println(e.getMessage());
		}

		PrintTable.print(customerService.getCustomerCoupons());

		System.out.println("********************************GET_CUSTOMER_COUPON**************************************");
		PrintTable.print(customerService.getCustomerCoupons());
		System.out
				.println("********************************GET_CUSTOMER_COUPON(CATEGORY)*****************************");
		PrintTable.print(customerService.getCustomerCoupons(Category.ELECTRICITY));

		System.out
				.println("********************************GET_CUSTOMER_COUPON(MAX PRICE)****************************");
		PrintTable.print(customerService.getCustomerCoupons(250));

		System.out
				.println("********************************GET_CUSTOMER_DTAILES**************************************");
		PrintTable.print(customerService.getCustomerDetails());

		System.out.println("********************************JUST_FOR_TESTING**************************************");
		// Test- to make sure that the coupon that related to customer is deleted.
		adminService.deleteCompany(2);
		PrintTable.print(adminService.getAllCompanies());

	}

}
