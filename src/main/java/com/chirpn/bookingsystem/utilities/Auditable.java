package com.chirpn.bookingsystem.utilities;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 * @author Satish Ghonge
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate = new Date();

	@CreatedBy
	private U createdBy;

	@LastModifiedBy
	private U lastModifiedBy;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
