package com.johnbryce.CouponSystem2.task;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.repo.CouponRepository;

@Component
@EnableScheduling
public class DailyJob {

	@Autowired
	private CouponRepository couponRepository;

	@Scheduled(fixedRate = (1000 * 60 * 60 * 24))
	public void deleteExpirationCoupon() {
		List<Coupon> coupons = couponRepository.findAll();
		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
				couponRepository.deleteById(coupon.getId());
			}

		}
	}
}
