package com.johnbryce.CouponSystem2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem2.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findById(int companyID);

}
