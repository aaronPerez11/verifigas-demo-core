package com.verificador.catalogos.utils.enums;

import lombok.Getter;

@Getter
public enum TipoErrorEnum {

	NOT_FOUND("NOT_FOUND"),
	BAD_REQUEST("BAD_REQUEST"),
	REQUEST_CONFLICT("REQUEST_CONFLICT"),
	MARCA_NO_ENCONTRADA("Marca Gasolinera No Encontrada"),
	MARCAS_NO_ENCONTRADAS("Marcas Gasolineras No Encontradas"),
	GASOLINERAS_NO_ENCONTRADAS("Gasolineras No Encontradas"),
	ESTACIONES_NO_ENCONTRADA("Estaciones Gasolineras No Encontradas"),
	ESTACION_NO_ENCONTRADA("Estación Gasolinera No Encontrada"),
	TIPOS_ERRORES_NO_ENCONTRADOS("Tipos de Errores No Encontrados"),
	CATALOGO_TIPOS_ERRORES_NO_ENCONTRADOS("Catalogo de Tipos de Errores no Encontrados"),
	CATALOGO_ERRORES_NO_ENCONTRADO("Catalogo de Errores no Encontrado"),
	TANQUE_NO_ENCONTRADO("Tanque no Encontrado");
	
	private String error;

	private TipoErrorEnum(String error) {
		this.error = error;
	}
	
	
}