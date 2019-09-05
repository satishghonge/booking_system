package com.demo.bookingsystem.utilities;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Satish Ghonge
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class Response<T> {

	private HttpStatus status = HttpStatus.OK;
	private String statusMessage;

	@JsonProperty("payload")
	private T data;

	private String imagePath;

	private String imageUrl;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		String str = "";
		str = str.concat("{\nstatus : " + status);
		str = str.concat("\nstatusMessage : " + statusMessage);
		str = str.concat("\n}");
		return str;
	}

}
