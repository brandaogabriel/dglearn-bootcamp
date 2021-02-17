package com.devgabriel.dglearn.resources.exceptions;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class OAuthCustomError implements Serializable {
	private static final long serialVersionUID = 1L;

	private String error;

	@JsonProperty("error_description")
	private String errorDescription;

	public OAuthCustomError() {
	}

	public OAuthCustomError(String error, String description) {
		this.error = error;
		this.errorDescription = description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String description) {
		this.errorDescription = description;
	}
}
