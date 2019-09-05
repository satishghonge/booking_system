package com.chirpn.bookingsystem.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chirpn.bookingsystem.entities.Booking;
import com.chirpn.bookingsystem.entities.User;
import com.chirpn.bookingsystem.exceptions.TravellingSystemException;
import com.chirpn.bookingsystem.services.UserService;
import com.chirpn.bookingsystem.utilities.PropertiesEntity;
import com.chirpn.bookingsystem.utilities.Response;
/**
 * @author Satish Ghonge
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends PropertiesEntity {
	
	static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@ResponseBody
	@PostMapping("/signup")
	public ResponseEntity<Response<User>> createUser(@RequestBody User user, HttpServletRequest httpRequest) {
		logger.info("Input For SignUp User {}: ", user);
		Response<User> response = new Response<>();
		try {
			if (userService.isUserEmailUnique(user.getEmail())) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setStatusMessage(DUPLICATE_EMAIL);
				logger.error(DUPLICATE_EMAIL);
				return new ResponseEntity<>(response, response.getStatus());
			}
			User userDb = userService.saveUser(user, httpRequest);
			response.setStatusMessage(REGISTER_SUCCESS);
			response.setData(userDb);
			logger.info(REGISTER_SUCCESS);
			response.setStatus(HttpStatus.OK);
			
		} catch (TravellingSystemException e) {
			logger.error("Error: {}", e.getMessage());
			response.setStatusMessage(e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(SERVER_ERROR +": {}", e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		logger.info("Exiting User SignUp with response :{}", response.getStatus());
		return new ResponseEntity<>(response, response.getStatus());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<User>> getUserById(@PathVariable(value = "id") Long userId) {
		Response<User> response = new Response<>();
		try {
			response = userService.findUserByHisId(userId);
		} catch (Exception e) {
			logger.error(SERVER_ERROR +": {}", e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		return new ResponseEntity<>(response, response.getStatus());
	}

	@GetMapping("/email")
	public ResponseEntity<Response<User>> getUserByEmail(@RequestParam(value = "email") String email,
			HttpServletRequest httpRequest) {
		Response<User> response = new Response<>();
		try {
			response = userService.findUserByEmail(email);
		} catch (Exception e) {
			logger.error(SERVER_ERROR +": {}", e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@PostMapping
	public ResponseEntity<Response<User>> createTravellingBooking(@RequestBody Booking booking, HttpServletRequest httpRequest) {
		logger.info("Input For SignUp User {}: ", booking);
		Response<User> response = new Response<>();
		try {
			User userDb = userService.saveBooking(booking, httpRequest);
			response.setStatusMessage("Booking has been done");
			response.setData(userDb);
			logger.info(REGISTER_SUCCESS);
			response.setStatus(HttpStatus.OK);
			
		} catch (TravellingSystemException e) {
			logger.error("Error: {}", e.getMessage());
			response.setStatusMessage(e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(SERVER_ERROR +": {}", e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		logger.info("Exiting User SignUp with response :{}", response.getStatus());
		return new ResponseEntity<>(response, response.getStatus());
	}

}