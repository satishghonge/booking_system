package com.demo.bookingsystem.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author Satish Ghonge
 *
 */
@JsonInclude(Include.NON_EMPTY)
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -3386186750193853239L;

	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String customerType;

	@Column(unique = true, nullable = false)
	private String email;

	@JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;

	@Column
	private String customerName;

	@Column
	private String favouriteRoute;

	@Column
	private String gender;

	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@OneToMany(mappedBy = "customerId", targetEntity = Booking.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Booking> booking = new HashSet<>();

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getFavouriteRoute() {
		return favouriteRoute;
	}


	public void setFavouriteRoute(String favouriteRoute) {
		this.favouriteRoute = favouriteRoute;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public Set<Booking> getBooking() {
		return booking;
	}


	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}


	@Override
	public String toString() {
		String str = "";
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			logger.error("Exception in method : {}", e.getMessage());
			e.printStackTrace();
		}
		return str;
	}

}
