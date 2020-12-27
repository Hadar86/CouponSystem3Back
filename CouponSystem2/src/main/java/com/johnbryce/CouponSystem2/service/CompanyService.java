package com.johnbryce.CouponSystem2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem2.beans.Category;
import com.johnbryce.CouponSystem2.beans.Company;
import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.exception.AllReadyExistException;
import com.johnbryce.CouponSystem2.exception.NoMatchException;

@Service
@Scope("prototype")

public class CompanyService extends ClientService {


	private int companyID;

	public boolean login(String email, String password) {
		
		List<Company> companies = companyRepository.findAll();
		for (Company company : companies) {
			System.out.println(company.getName());
			System.out.println(company.getId());
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				companyID = company.getId();
				System.out.println("WELCOME");
				return true;
			}
		}
		System.out.println("SORRY");
		return false;
	}

	public void addCoupon(Coupon coupon) throws Exception {
		Company company = companyRepository.getOne(this.companyID);
		List<Coupon> Coupons = company.getCoupons();
		for (Coupon coup : Coupons) {

			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new Exception("COUPON TITLE CANT BE THE SAME");
			}
		}
		coupon.setCompanyID(this.companyID);
		couponRepository.save(coupon);
	}

	public void updateCoupon(Coupon coupon) throws AllReadyExistException, NoMatchException {
		List<Coupon> Coupons = couponRepository.findAll();
		for (Coupon coup : Coupons) {
			if (coup == null) {
				throw new NoMatchException("COUPON DOES NOT EXIST");
			}
			if (coup.getCompanyID() == coupon.getCompanyID() && coup.getId() == coupon.getId()) {
				companyRepository.findById(coupon.getId());
				couponRepository.saveAndFlush(coupon);
				return;
			}
		}
		throw new NoMatchException("CANT CHANGE COUPON.ID OR COMPANY.ID");
	}

	public void deleteCoupon(int couponID) {
		Coupon coup = couponRepository.getOne(couponID);
		if (coup.getCompanyID() == companyID) {
			couponRepository.deleteById(couponID);
		}
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		List<Coupon> resultCoupons = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCompanyID() == companyID) {
				resultCoupons.add(coup);
			}
		}
		return resultCoupons;
	}

	public List<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				result.add(coupon);
			}
		}
		return result;
	}

	public List<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				result.add(coupon);
			}
		}
		return result;
	}

	public Company getCompanyDetails() {
		List<Coupon> companyCoupons = getCompanyCoupons();
		Company company = companyRepository.findById(companyID);
		company.setCoupons(companyCoupons);
		return company;
	}

}
