package com.johnbryce.CouponSystem2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findById(int customerId);

	List<Customer> findCustomerByCouponsContains(Coupon coupon);

}
