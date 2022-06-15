package com.verificador.catalogos.exception;


import lombok.Getter;

@Getter
public class ErrorResponseModel {
	private String mensaje;
	private String detalle;
	
	 public ErrorResponseModel(String mensaje, String detalle) {
	        this.mensaje = mensaje;
	        this.detalle = detalle;
	 }

}
