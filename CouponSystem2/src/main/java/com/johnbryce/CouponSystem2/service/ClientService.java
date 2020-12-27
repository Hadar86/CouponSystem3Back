package com.johnbryce.CouponSystem2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem2.repo.CompanyRepository;
import com.johnbryce.CouponSystem2.repo.CouponRepository;
import com.johnbryce.CouponSystem2.repo.CustomerRepository;

import lombok.Data;

@Service
@Data
public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CouponRepository couponRepository;
	@Autowired
	protected CustomerRepository customerRepository;

	public abstract boolean login(String email, String password);

}
