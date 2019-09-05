package com.chirpn.bookingsystem.services;

import javax.servlet.http.HttpServletRequest;

import com.chirpn.bookingsystem.entities.Booking;
import com.chirpn.bookingsystem.entities.User;
import com.chirpn.bookingsystem.utilities.Response;

public interface UserService {
	
	void updateMetadata(Object user) throws Exception;
	User saveUser(User user, HttpServletRequest request) throws Exception;
	boolean isUserEmailUnique(String email) throws Exception;
	User getLoginUser() throws Exception;
	Response<User> findUserByHisId(Long userId);
	Response<User> findUserByEmail(String email);
	User saveBooking(Booking booking, HttpServletRequest request) throws Exception; 

}
