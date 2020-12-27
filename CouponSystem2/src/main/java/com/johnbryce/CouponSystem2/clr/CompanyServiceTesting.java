package com.johnbryce.CouponSystem2.clr;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.beans.Category;
import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.exception.AllReadyExistException;
import com.johnbryce.CouponSystem2.exception.NoMatchException;
import com.johnbryce.CouponSystem2.exception.WrongException;
import com.johnbryce.CouponSystem2.print.PrintTable;
import com.johnbryce.CouponSystem2.service.CompanyService;

//@Component
//@Order(3)
public class CompanyServiceTesting implements CommandLineRunner {

	@Autowired
	private CompanyService companyService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("   ____ ___  __  __ ____   _    _   ___   __  ____  _____ ______     _____ ____ _____ \r\n"
				+ "  / ___/ _ \\|  \\/  |  _ \\ / \\  | \\ | \\ \\ / / / ___|| ____|  _ \\ \\   / /_ _/ ___| ____|\r\n"
				+ " | |  | | | | |\\/| | |_) / _ \\ |  \\| |\\ V /  \\___ \\|  _| | |_) \\ \\ / / | | |   |  _|  \r\n"
				+ " | |__| |_| | |  | |  __/ ___ \\| |\\  | | |    ___) | |___|  _ < \\ V /  | | |___| |___ \r\n"
				+ "  \\____\\___/|_|  |_|_| /_/   \\_\\_| \\_| |_|___|____/|_____|_| \\_\\ \\_/  |___\\____|_____|\r\n"
				+ "                                        |_____|                                       ");

		System.out.println("********************************LOGIN_COMPANY**************************************");
		System.out.println(companyService.login("nike@nike.com", "Bb@12345"));

		System.out.println("********************************LOGIN_COMPANY NOT WORKING***************************");

		System.out.println(companyService.login("nike@KKK.com", "5469"));

		System.out.println("********************************ADD_COUPON**************************************");
		Coupon coup6 = new Coupon(5, Category.FOOD, "Mogo", "Mogo Mogo", Date.valueOf("2020-01-01"),
				Date.valueOf("2021-09-01"), 10, 100.0, "BLA");
		Coupon coup7 = new Coupon(7, Category.ELECTRICITY, "ALAM", "STORE", Date.valueOf("2019-05-14"),
				Date.valueOf("2022-05-13"), 0, 200, "BLA");
//		Coupon coup11 = new Coupon(8, Category.ELECTRICITY, "ALAM", "STORE", Date.valueOf("2019-05-14"),
//				Date.valueOf("2022-05-13"), 80, 200, "BLA");
//		Coupon coup9 = new Coupon(9, Category.VACATION, "GO", "TOURING", Date.valueOf("2019-05-14"),
//				Date.valueOf("2022-05-13"), 100, 500, "BLA");
//		Coupon coup10 = new Coupon(10, Category.VACATION, "DOIT", "BYE", Date.valueOf("2019-05-14"),
//				Date.valueOf("2022-05-13"), 50, 700, "BLA");
		try {
			companyService.addCoupon(coup6);
			companyService.addCoupon(coup7);
//			companyService.addCoupon(coup11);
//			companyService.addCoupon(coup9);
//			companyService.addCoupon(coup10);

			PrintTable.print(companyService.getCompanyCoupons());

		} catch (AllReadyExistException | WrongException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(
				"********************************ADD_COUPON NOT WORKING**************************************");
		Coupon coup8 = new Coupon(6, Category.FOOD, "Mogo", "Mogo Mogo", Date.valueOf("2020-01-01"),
				Date.valueOf("2021-09-01"), 10, 100.0, "BLA");
		try {
			companyService.addCoupon(coup8);
			PrintTable.print(companyService.getCompanyCoupons());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("********************************UPDATE_COUPON**************************************");
		try {
			coup6.setPrice(129.9);
			companyService.updateCoupon(coup6);

			PrintTable.print(companyService.getCompanyCoupons());

		} catch (AllReadyExistException | NoMatchException e) {
			System.out.println(e.getMessage());

		}
		System.out.println(
				"********************************UPDATE_COUPON NOT WORKINK**************************************");
		try {
			coup6.setId(20);
			companyService.updateCoupon(coup6);

			PrintTable.print(companyService.getCompanyCoupons());

		} catch (AllReadyExistException | NoMatchException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("********************************DELETE_COUPON**************************************");

		companyService.deleteCoupon(coup7.getId());
		PrintTable.print(companyService.getCompanyCoupons());

		System.out.println("********************************GET_COMPANY_COUPONS (CATEGORY)*********************");

		PrintTable.print(companyService.getCompanyCoupons(Category.FOOD));

		System.out.println("********************************GET_COMPANY_COUPONS (MAX PRICE)********************");

		PrintTable.print(companyService.getCompanyCoupons(150));

		System.out.println("********************************GET_COMPANY_DETAILS********************");
		PrintTable.print(companyService.getCompanyDetails());
	}

}
