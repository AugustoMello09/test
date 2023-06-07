package com.io.github.AugustoMello09.Locadora.resources.exception;

public class FieldMessage {

	private String fieldName;
	private String message;

	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public FieldMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
