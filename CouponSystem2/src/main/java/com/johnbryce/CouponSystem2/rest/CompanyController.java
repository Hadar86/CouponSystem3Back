package com.johnbryce.CouponSystem2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem2.beans.Category;
import com.johnbryce.CouponSystem2.beans.Company;
import com.johnbryce.CouponSystem2.beans.Coupon;

import com.johnbryce.CouponSystem2.service.CompanyService;

@RestController
@RequestMapping("companies")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			if(companyService.login(email, password))
			{
				return new ResponseEntity<>(companyService.getCompanyDetails(), HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
		try {
			companyService.addCoupon(coupon);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
		try {
			companyService.updateCoupon(coupon);
			return new ResponseEntity<>("Updated successfuly", HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteCoupon/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable(name = "id") int id){
		try {
			companyService.deleteCoupon(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("getAllCompanyCoupons")
	public ResponseEntity<?> getCompanyCoupons() {
		return new ResponseEntity<>(companyService.getCompanyCoupons(), HttpStatus.OK);
	}

	@GetMapping("getAllCompanyCouponsCategory")
	public ResponseEntity<?> getCompanyCoupons(Category category) {
		return new ResponseEntity<>(companyService.getCompanyCoupons(category), HttpStatus.OK);
	}
	@GetMapping("getAllCompanyCouponsMaxPrice")
	public ResponseEntity<?> getCompanyCoupons(double maxPrice) {
		return new ResponseEntity<>(companyService.getCompanyCoupons(maxPrice), HttpStatus.OK);
	}
	
	@GetMapping("getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestBody Company company) {
		companyService.getCompanyDetails();
		return new ResponseEntity<>(companyService.getCompanyDetails(), HttpStatus.OK);
	}

}
