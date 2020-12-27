package com.johnbryce.CouponSystem2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.tokens.CustomerTokenManager;

//@Component
public class TokenClearing<CustomerTokensManager> {
	@Autowired
	private CustomerTokenManager tokensManager;
	
	@Scheduled(fixedRate = (1000 * 60 * 30))
	public void deleteExpiredTokenOver30Min() {
	tokensManager.deleteExpiredToken();
	}
}

