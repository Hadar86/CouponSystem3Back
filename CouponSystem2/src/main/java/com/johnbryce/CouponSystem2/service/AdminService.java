package com.johnbryce.CouponSystem2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem2.beans.Company;
import com.johnbryce.CouponSystem2.beans.Coupon;
import com.johnbryce.CouponSystem2.beans.Customer;
import com.johnbryce.CouponSystem2.exception.AllReadyExistException;
import com.johnbryce.CouponSystem2.exception.NoMatchException;
import com.johnbryce.CouponSystem2.exception.WrongException;

@Service
public class AdminService extends ClientService {
	private static final String EMAIL = "admin@admin.com";
	private static final String PASSWORD = "Aa!12345";

	public boolean login(String email, String password) {
		if (email.equals(EMAIL) && password.equals(PASSWORD)) {
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws AllReadyExistException {
		List<Company> companies = getAllCompanies();
		for (Company comp : companies) {
			if (comp.getName().equals(company.getName()) || comp.getEmail().equals(company.getEmail())) {
				throw new AllReadyExistException("COMPANY.NAME OR COMPAMY.EMAIL CANT MATCH");
			}
		}
		companyRepository.save(company);
	}

	public void updateCompany(Company company) throws NoMatchException {
		List<Company> companies = companyRepository.findAll();
		for (Company comp : companies) {
			if (comp.getId() == company.getId() && comp.getName().equals(company.getName())) {
				companyRepository.saveAndFlush(company);
				return;
			}
		}
		throw new NoMatchException("COMPANY_ID AND COMPANY_NAME CANT BE CHANGED!");

	}

	public void deleteCompany(int companyID) throws NoMatchException {
		List<Coupon> companyCoupons = companyRepository.getOne(companyID).getCoupons();

		for (int i = 0; i < companyCoupons.size(); i++) {
			deleteCouponForCustomers(companyCoupons.get(i));
		}

		couponRepository.deleteAll(companyCoupons);
		companyRepository.deleteById(companyID);

	}

// I created another method to help delete the coupon that related to customer and company by ID.
	private void deleteCouponForCustomers(Coupon coupon) {
		List<Customer> customers = getAllCustomers();
		for (Customer cust : customers) {
			List<Coupon> customerCoupons = cust.getCoupons();

			for (int i = 0; i < customerCoupons.size(); i++) {
				Coupon coup = customerCoupons.get(i);
				if (coup.getId() == coupon.getId()) {
					customerCoupons.remove(i);
				}
			}
			cust.setCoupons(customerCoupons);
			customerRepository.saveAndFlush(cust);
		}
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getOneCompany(int companyID) {
		return companyRepository.findById(companyID);
	}

	public void addCustomer(Customer customer) throws AllReadyExistException {
		List<Customer> customers = getAllCustomers();
		for (Customer cust : customers) {
			if (cust.getEmail().equals(customer.getEmail())) {
				throw new AllReadyExistException("CUSTOMER CANT BE ADDED- SAME EMAIL");
			}
		}
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) throws NoMatchException, WrongException {

		List<Customer> customers = customerRepository.findAll();
		for (Customer cust : customers) {

			if (cust.getId() == customer.getId()) {
				customerRepository.saveAndFlush(customer);
				return;
			} else {
			}
		}
		throw new NoMatchException("CANT CHANGE CUSTOMER ID");

	}

	public void deleteCustomer(int customerID) {
		customerRepository.deleteById(customerID);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getOneCustomer(int customerID) {
		return customerRepository.findById(customerID);
	}

}
