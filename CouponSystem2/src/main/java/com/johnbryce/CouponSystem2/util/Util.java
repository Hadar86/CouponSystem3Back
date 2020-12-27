package com.johnbryce.CouponSystem2.util;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.beans.Category;
import com.johnbryce.CouponSystem2.beans.Company;
import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.beans.Customer;
import com.johnbryce.CouponSystem2.repo.CompanyRepository;
import com.johnbryce.CouponSystem2.repo.CouponRepository;
import com.johnbryce.CouponSystem2.repo.CustomerRepository;

@Component
@Order(1)
public class Util implements CommandLineRunner {
	public static String LIGHT="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIVpnc5ky5LTQRsPzqCORKxR_kAhiSglA0Mg&usqp=CAU";
	public static String BENZ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRps_beQLtBeyyLkobAXg1WucncnO46qDceXQ&usqp=CAU";
	public static String PASTA="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0twwlSomBt2i33P78KY5SSj5-0C3X9hWAkA&usqp=CAU";
	public static String SUSHI="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYJcbC57GyDHN500cTH394Hry_x4m9Vk49jQ&usqp=CAU";
	public static String HUDSON="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcKttthC8GKt-e78yx0uYb7NnOXayWnyI7FA&usqp=CAU";
	public static String MOGO="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT11bRaFjj_2Ks-bUG8lPtT-hkXiCZGrLdTpQ&usqp=CAU";
	public static String VIEW="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPwFGMe41eamcfx81Fb_s17FnaZFmP58OnOw&usqp=CAU";

	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {

		Company p1 = new Company("NIKE", "nike@nike.com", "Bb@12345", null);
		Company p2 = new Company("COLA", "cola@cola.com", "5678", null);
		Company p3 = new Company("MILKA", "milka@milka.com", "Cc@12345", null);
		Company p4 = new Company("HAVAIANAS", "havaianas@havaianas.com", "Bb@12345", null);
		Company p5 = new Company("NEXT", "next@next.com", "Bb@12345", null);

		companyRepository.save(p1);
		companyRepository.save(p2);
		companyRepository.save(p3);
		companyRepository.save(p4);
		companyRepository.save(p5);

		Customer t1 = new Customer("Kobi", "Shasha", "kobi@gmail.com", "Bb@12345", null);
		Customer t2 = new Customer("Ido", "Zahavy", "ido@gmail.com", "Bb@12345", null);
		Customer t3 = new Customer("Yotam", "Klainshtain", "yotam@gmail.com", "Bb@12345", null);
		Customer t4 = new Customer("Dean", "Klainshtain", "dean@dean.com", "Bb@12345", null);
		Customer t5 = new Customer("Rotem", "Shaham", "rotem@rotem.com", "Bb@12345", null);

		customerRepository.save(t1);
		customerRepository.save(t2);
		customerRepository.save(t3);
		customerRepository.save(t4);
		customerRepository.save(t5);
		
	

		Coupon coup1 = new Coupon(3, Category.ELECTRICITY, "AVISOFER", "STORE", Date.valueOf("2019-05-14"),
				Date.valueOf("2022-12-13"), 20, 200, LIGHT);
		Coupon coup2 = new Coupon(2, Category.RESTAURANT, "BENZ", "Best", Date.valueOf("2020-01-01"),
				Date.valueOf("2023-12-01"), 90, 50, BENZ);
		Coupon coup3 = new Coupon(1, Category.FOOD, "PASTA", "Red Pasta", Date.valueOf("2019-02-01"),
				Date.valueOf("2021-01-01"), 50, 70, PASTA);
		Coupon coup4 = new Coupon(3, Category.FOOD, "SUSHI", "Yam", Date.valueOf("2019-07-01"),
				Date.valueOf("2021-03-30"), 20, 120, SUSHI);
		Coupon coup5 = new Coupon(1, Category.RESTAURANT, "HUDSON", "Yami", Date.valueOf("2020-01-01"),
				Date.valueOf("2021-01-01"), 9, 300, HUDSON);
		Coupon coup6 = new Coupon(5, Category.FOOD, "MOGO", "Chop Chop", Date.valueOf("2020-01-01"),
				Date.valueOf("2021-09-01"), 10, 100.0, MOGO);
		Coupon coup7 = new Coupon(7, Category.ELECTRICITY, "ALAM", "STORE", Date.valueOf("2019-05-14"),
				Date.valueOf("2022-05-13"), 60, 200, LIGHT);
		Coupon coup8 = new Coupon(8, Category.ELECTRICITY, "ALAM", "STORE", Date.valueOf("2019-05-14"),
				Date.valueOf("2022-08-13"), 80, 200, LIGHT);
		Coupon coup9 = new Coupon(9, Category.VACATION, "GO", "TOURING", Date.valueOf("2019-05-14"),
				Date.valueOf("2022-09-13"), 100, 500, VIEW);
		Coupon coup10 = new Coupon(10, Category.VACATION, "DOIT", "BYE", Date.valueOf("2019-05-14"),
				Date.valueOf("2022-05-13"), 50, 700, VIEW);

		couponRepository.save(coup1);
		couponRepository.save(coup2);
		couponRepository.save(coup3);
		couponRepository.save(coup4);
		couponRepository.save(coup5);
		couponRepository.save(coup6);
		couponRepository.save(coup7);
		couponRepository.save(coup8);
		couponRepository.save(coup9);
		couponRepository.save(coup10);

		t1 = customerRepository.getOne(t1.getId());
		t1.getCoupons().add(coup10);
		t1.getCoupons().add(coup9);
		customerRepository.saveAndFlush(t1);
		t5 = customerRepository.getOne(t5.getId());
		t5.getCoupons().add(coup8);
		t5.getCoupons().add(coup7);
		t4 = customerRepository.getOne(t4.getId());
		t4.getCoupons().add(coup6);

	}

}
