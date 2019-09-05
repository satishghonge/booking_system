package com.demo.bookingsystem.utilities;

import java.util.Observable;
/**
 * @author Satish Ghonge
 *
 */
public class PropertiesEntity extends Observable {
	
	public static final String UNAUTHORIZE = "Request Not Authorized";
	protected static final String FORGET_PASSWORD_RESET = "Your password has been reset successfully";
	protected static final String CHANGE_PASSWORD = "Your password has been change successfully";
	protected static final String REGISTER_SUCCESS = "You have registered successfully.";
	protected static final String REGISTER_ERROR = "Error in register user";
	protected static final String DUPLICATE_EMAIL = "User already exists with same Email Address";
	protected static final String SERVER_ERROR = "Internal server error";
	protected static final String ACCOUNT_ACTIVATE_SUCCESS = "The account has been confirmed please login to your account now";

}
