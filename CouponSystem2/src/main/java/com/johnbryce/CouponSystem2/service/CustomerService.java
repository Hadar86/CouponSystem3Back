package com.johnbryce.CouponSystem2.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem2.beans.Category;
import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.beans.Customer;
import com.johnbryce.CouponSystem2.exception.AllReadyExistException;
import com.johnbryce.CouponSystem2.exception.WrongException;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerID;

	public boolean login(String email, String password) {
		List<Customer> customers = customerRepository.findAll();
		for (Customer customer : customers) {
			if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
				customerID = customer.getId();
				System.out.println("WELCOME");
				return true;
			}
		}
		System.out.println("SORRY");
		return false;
	}

	public void purchaseCoupon(Coupon coupon) throws WrongException, AllReadyExistException {
		List<Coupon> coupons = getCustomerCoupons();
		for (Coupon coup : coupons) {
			if (coupon.getId() == coup.getId()) {
				throw new AllReadyExistException("COUPON ALLREADY EXIST!");
			}
		}
		if (coupon.getAmount() == 0) {
			throw new WrongException("THERE IS NO COUPONS IN STOCK!");
		}
		if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
			throw new WrongException("THE COUPON DATE IS EXPIRED");
		}

		coupon.setAmount(coupon.getAmount() - 1);
		couponRepository.saveAndFlush(coupon);
		Customer customer = customerRepository.findById(customerID);
		customer.getCoupons().add(coupon);
		customerRepository.saveAndFlush(customer);
	}

	public List<Coupon> getCustomerCoupons() {
		Customer customer = customerRepository.getOne(customerID);
		return customer.getCoupons();
	}

	public List<Coupon> getCustomerCoupons(Category category) {
		List<Coupon> coupons = getCustomerCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				result.add(coupon);
			}
		}
		return result;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> coupons = getCustomerCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				result.add(coupon);
			}
		}
		return result;
	}

	public Customer getCustomerDetails() {
		List<Coupon> customerCoupons = getCustomerCoupons();
		Customer customer = customerRepository.findById(customerID);
		customer.setCoupons(customerCoupons);
		return customer;

	}

	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		return coupons;

	}
}
