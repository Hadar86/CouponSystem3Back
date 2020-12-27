package com.johnbryce.CouponSystem2.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.CouponSystem2.login.ClientType;
import com.johnbryce.CouponSystem2.login.LogingManager;

//@Component
//@Order(5)
public class LoginManagerTest implements CommandLineRunner {

	@Autowired
	private LogingManager logingManager;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" _     ___   ____ ___ _   _     __  __    _    _   _    _    ____ _____ ____  \r\n"
				+ " | |   / _ \\ / ___|_ _| \\ | |   |  \\/  |  / \\  | \\ | |  / \\  / ___| ____|  _ \\ \r\n"
				+ " | |  | | | | |  _ | ||  \\| |   | |\\/| | / _ \\ |  \\| | / _ \\| |  _|  _| | |_) |\r\n"
				+ " | |__| |_| | |_| || || |\\  |   | |  | |/ ___ \\| |\\  |/ ___ \\ |_| | |___|  _ < \r\n"
				+ " |_____\\___/ \\____|___|_| \\_|___|_|  |_/_/   \\_\\_| \\_/_/   \\_\\____|_____|_| \\_\\\r\n"
				+ "                           |_____|                                             ");

		System.out.println("********************************LOGIN**************************************");

		logingManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		logingManager.login("nike@nike.com", "1234", ClientType.COMPANY);
		logingManager.login("kobi@gmail.com", "1234", ClientType.CUSTOMER);
		System.out.println("********************************LOGIN_NOT_WORKING**************************************");

		logingManager.login("admin@222.com", "admin", ClientType.ADMINISTRATOR);
		logingManager.login("nike@ekin.com", "1234", ClientType.COMPANY);
		logingManager.login("kobi@gmail.com", "0123", ClientType.CUSTOMER);

	}

}
