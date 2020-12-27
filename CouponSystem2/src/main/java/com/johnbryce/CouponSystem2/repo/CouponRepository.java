package com.johnbryce.CouponSystem2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem2.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findById(int couponID);

}
