package com.verificador.catalogos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionBadRequest extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExceptionBadRequest(String exception) {
		super(exception);
	}
	

}
