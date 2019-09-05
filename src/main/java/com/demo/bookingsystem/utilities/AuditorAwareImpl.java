package com.demo.bookingsystem.utilities;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.demo.bookingsystem.entities.User;
import com.demo.bookingsystem.services.UserService;
/**
 * @author Satish Ghonge
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Autowired
	private UserService userService;
	protected static final Logger logger = Logger.getLogger(AuditorAwareImpl.class);

	@Override
	public String getCurrentAuditor() {
		try {
			User user = userService.getLoginUser();
			if (user != null) {
				return user.getEmail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}