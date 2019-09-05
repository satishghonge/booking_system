package com.demo.bookingsystem.services;

import javax.servlet.http.HttpServletRequest;

import com.demo.bookingsystem.entities.Booking;
import com.demo.bookingsystem.entities.User;
import com.demo.bookingsystem.utilities.Response;

public interface UserService {
	
	void updateMetadata(Object user) throws Exception;
	User saveUser(User user, HttpServletRequest request) throws Exception;
	boolean isUserEmailUnique(String email) throws Exception;
	User getLoginUser() throws Exception;
	Response<User> findUserByHisId(Long userId);
	Response<User> findUserByEmail(String email);
	User saveBooking(Booking booking, HttpServletRequest request) throws Exception; 

}
