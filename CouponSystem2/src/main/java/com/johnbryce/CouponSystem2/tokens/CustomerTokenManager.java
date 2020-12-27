package com.johnbryce.CouponSystem2.tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.exception.WrongException;
import com.johnbryce.CouponSystem2.security.UserData;
import com.johnbryce.CouponSystem2.service.CustomerService;

@Component
public class CustomerTokenManager {

	private Map<String, UserData> tokenMap = new HashMap<>();

	public String addToken(CustomerService customerService) {
		UserData userData = new UserData(customerService, System.currentTimeMillis());
		String token = UUID.randomUUID().toString();
		tokenMap.put(token, userData);
		return token;
	}
	public UserData getUserData(String token) {
		return tokenMap.get(token);
		
	}

	public boolean isTokenExist(String token) throws WrongException {
		if (tokenMap.containsKey(token)) {
			return true;
		}
		throw new WrongException("sorry");
	}

	public void deleteExpiredToken() {
		
	}
}