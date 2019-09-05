package com.demo.bookingsystem.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author Satish Ghonge
 *
 */

@Entity
@Table(name = "booking")
public class Booking implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private User customerId;
	
	@Column(name = "journey_start_date_time")
	private Timestamp journeyStartDateTime;
	
	@Column(name = "route_name")
	private String routeName;
	
	@Column(name = "price_of_journey")
	private Double priceOfJourney;
	
	@Column(name = "currency")
	private String currency = "$";
	
	@Transient
	private Long custId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCustomerId() {
		return customerId;
	}

	public void setCustomerId(User customerId) {
		this.customerId = customerId;
	}

	public Timestamp getJourneyStartDateTime() {
		return journeyStartDateTime;
	}

	public void setJourneyStartDateTime(Timestamp journeyStartDateTime) {
		this.journeyStartDateTime = journeyStartDateTime;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	public Double getPriceOfJourney() {
		return priceOfJourney;
	}

	public void setPriceOfJourney(Double priceOfJourney) {
		this.priceOfJourney = priceOfJourney;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		String str = "";
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	

}
