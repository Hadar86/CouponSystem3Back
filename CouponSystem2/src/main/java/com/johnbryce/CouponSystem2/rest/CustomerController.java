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
import com.johnbryce.CouponSystem2.beans.Customer;
import com.johnbryce.CouponSystem2.service.CompanyService;
import com.johnbryce.CouponSystem2.service.CustomerService;
import com.johnbryce.CouponSystem2.tokens.CustomerTokenManager;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerTokenManager customerTokenManager;

	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			if(customerService.login(email, password))
			{
				return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon
//			, @RequestHeader("Authorization") String token
			) {
		try {
//			CustomerService customerService = (CustomerService)customerTokenManager.getUserData(token).getClientService();
			customerService.purchaseCoupon(coupon);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getAllCustomerCoupons")
	public ResponseEntity<?> getCustomerCoupons(
//	@RequestHeader("Authorization") String token
	) {
//		CustomerService customerService = (CustomerService)customerTokenManager.getUserData(token).getClientService();
		customerService.getCustomerCoupons();
		return new ResponseEntity<>(customerService.getCustomerCoupons(), HttpStatus.OK);
	}

	@GetMapping("getAllCustomerCouponsCategory")
	public ResponseEntity<?> getCustomerCoupons(Category category
//			, @RequestHeader("Authorization") String token
			) {
//		CustomerService customerService = (CustomerService)customerTokenManager.getUserData(token).getClientService();
		customerService.getCustomerCoupons(category);
		return new ResponseEntity<>(customerService.getCustomerCoupons(category), HttpStatus.OK);
	}

	@GetMapping("getAllCustomerCouponsMaxPrice")
	public ResponseEntity<?> getCustomerCoupons(double maxPrice
//			, @RequestHeader("Authorization") String token
			) {
//		CustomerService customerService = (CustomerService)customerTokenManager.getUserData(token).getClientService();
		customerService.getCustomerCoupons(maxPrice);
		return new ResponseEntity<>(customerService.getCustomerCoupons(maxPrice), HttpStatus.OK);
	}

	@GetMapping("getCustomerDetails/{id}")
	public ResponseEntity<?> getCustomerDetails(@RequestBody Customer customer
//			, @RequestHeader("Authorization") String token
			) {
//		CustomerService customerService = (CustomerService)customerTokenManager.getUserData(token).getClientService();
		customerService.getCustomerDetails();
		return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.OK);
	}
	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(){
		return new ResponseEntity<>(customerService.getAllCoupons(), HttpStatus.OK);
	}

}
