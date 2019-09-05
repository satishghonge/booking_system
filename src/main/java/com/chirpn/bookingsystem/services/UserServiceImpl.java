package com.chirpn.bookingsystem.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chirpn.bookingsystem.entities.Booking;
import com.chirpn.bookingsystem.entities.User;
import com.chirpn.bookingsystem.exceptions.TravellingSystemException;
import com.chirpn.bookingsystem.repository.UserRepository;
import com.chirpn.bookingsystem.utilities.CommonUtils;
import com.chirpn.bookingsystem.utilities.Response;
/**
 * @author Satish Ghonge
 *
 */
@Service(value = "userService")
@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = true)
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(0, new SimpleGrantedAuthority("ADMIN"));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				grantedAuthorities);
	}

	@Transactional(rollbackFor = { java.lang.Throwable.class })
	@Override
	public User saveUser(User user, HttpServletRequest request) throws Exception {
		if (user.getPassword() == null)
			throw new Exception("Password Not Found");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userDb = userRepository.save(user);
		if (userDb == null) {
			throw new TravellingSystemException("Error In save user In saveUSer Dao");
		}
		logger.info("register user= {}", user);
		
		return userDb;
	}

	public User getLoginUser() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			throw new AuthenticationException();
		String username = authentication.getName();
		if (username == null || username.isEmpty())
			throw new AuthenticationException();
		return userRepository.findByEmail(username);
	}

	
	@Override
	public void updateMetadata(Object user) throws Exception {
		java.sql.Timestamp timestamp = new java.sql.Timestamp(new Date().getTime());
		if (user instanceof User) {
			//((User) user).setLastModifiedBy(timestamp.toString());
		}
	}

	@Override
	public boolean isUserEmailUnique(String email) throws Exception {
		boolean result = false;
		User user = userRepository.findByEmail(email);
		if (user != null)
			result = true;
		return result;
	}

	@Override
	public Response<User> findUserByHisId(Long userId) {
		Response<User> response = new Response<>();
		if (userId == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setStatusMessage("Please provide the user Id");
			return response;
		}
		User userDb = userRepository.findOne(userId);
		if (userDb == null) {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setStatusMessage("User Not Found");
			return response;
		}
		response.setData(userDb);
		response.setStatus(HttpStatus.OK);
		response.setStatusMessage("User Found");
		return response;
		
	}

	@Override
	public Response<User> findUserByEmail(String email) {
		Response<User> response = new Response<>();
		if (CommonUtils.isNullOrEmpty(email)) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setStatusMessage("Please provide the email");
			return response;
		}
		User userDb = userRepository.findOneByEmail(email);
		if (userDb == null) {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setStatusMessage("User Not Found");
			return response;
		}
		response.setData(userDb);
		response.setStatus(HttpStatus.OK);
		response.setStatusMessage("User Found");
		return response;
	}

	@Transactional(rollbackFor = { java.lang.Throwable.class })
	@Override
	public User saveBooking(Booking booking, HttpServletRequest request) throws Exception {
		User users = userRepository.findById(booking.getCustId());
		if(users.getCustomerType().equalsIgnoreCase("VIP")) {
			booking.setPriceOfJourney(calculateBooking(1d, booking, users));
		} else if (users.getCustomerType().equalsIgnoreCase("Normal")) {
			booking.setPriceOfJourney(calculateBooking(2d, booking, users));
		} else {
			booking.setPriceOfJourney(2d);
		}
		//booking.setCustomerId(users);
		Set<Booking> bookings = new HashSet<>();
		bookings.add(booking);
		users.setBooking(bookings);
		userRepository.save(users);
		return users;
	}
	
	private Double calculateBooking(Double bookingPrice, Booking booking, User user) {
		if(user.getFavouriteRoute().equalsIgnoreCase(booking.getRouteName())) {
			Double price = 30/100 * bookingPrice;
			Double priceAfterCalculateDiscount = bookingPrice - price;
			return priceAfterCalculateDiscount;
			
		}
		return 0d;
	   
	}
	
}
