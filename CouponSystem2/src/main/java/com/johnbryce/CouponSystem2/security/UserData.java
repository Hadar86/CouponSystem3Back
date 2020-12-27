package com.johnbryce.CouponSystem2.security;

import com.johnbryce.CouponSystem2.service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {
	
	private ClientService clientService;
	private long timestamp;
	
}

