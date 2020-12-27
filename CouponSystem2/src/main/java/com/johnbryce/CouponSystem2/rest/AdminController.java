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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem2.beans.Company;
import com.johnbryce.CouponSystem2.beans.Customer;
import com.johnbryce.CouponSystem2.exception.NoMatchException;
import com.johnbryce.CouponSystem2.exception.WrongException;
import com.johnbryce.CouponSystem2.service.AdminService;
import com.johnbryce.CouponSystem2.service.CompanyService;
import com.johnbryce.CouponSystem2.service.CustomerService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			if(adminService.login(email, password))
			{
				return new ResponseEntity<>(adminService.login(email, password), HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		try {
			adminService.addCompany(company);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {
		try {
			adminService.updateCompany(company);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (NoMatchException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("deleteCompany/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") int id) throws Exception {
		adminService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAllCompanies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping("getOneCompany/{id}")
	public ResponseEntity<?> getOneCompany(@PathVariable(name = "id") int id) {
		return new ResponseEntity<>(adminService.getOneCompany(id), HttpStatus.OK);
	}

	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			adminService.addCustomer(customer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {

		try {
			adminService.updateCustomer(customer);
			return new ResponseEntity<>("Updated successfuly", HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") int id) {
		adminService.deleteCustomer(id);
		return new ResponseEntity<>("Deleted successfuly", HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("getOneCustomer/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable(name = "id") int id) {
		return new ResponseEntity<>(adminService.getOneCustomer(id), HttpStatus.OK);
	}

}
