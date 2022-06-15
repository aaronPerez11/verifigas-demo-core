package com.verificador.catalogos.web.model;

import java.util.Objects;

import com.verificador.catalogos.domain.entity.ErrorSistema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder(toBuilder = true)
@Schema(name = "Error Sistema Model", description = "Modelo de datos catalogo de tipos de errores")
public class ErrorSistemaModel {

	@Schema(description = "Identificador único del catalogo del tipo de error", example = "1")
	private Long id;
	
	@Schema(description = "Identificador único del tipo de error", example = "1")
	private Long idTipoError;
	
	@Schema(description = "Titulo del catalogo de tipo de error", example = "Encriptación de Información")
	private String titulo;
	
	@Schema(description = "Descripción del catalogo de tipo de error", example = "Analizando si la base de datos esta encriptada")
	private String descripcion;
	
	@Tolerate
	protected ErrorSistemaModel() {
		
	}
	
	public static ErrorSistemaModel crearErrorSistemaModel(ErrorSistema error) {
		if(Objects.nonNull(error)) {
			return ErrorSistemaModel.builder()
					.id(error.getId())
					.idTipoError(error.getTipoError().getId())
					.titulo(error.getTitulo())
					.descripcion(error.getDescripcion())
					.build();
		}
		
		return null;
	}
	
	
}
