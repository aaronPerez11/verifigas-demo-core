package com.verificador.catalogos.web.model;

import java.util.Objects;

import com.verificador.catalogos.domain.entity.TipoError;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder(toBuilder = true)
@Schema(name = "Tipo Error Model", description = "Modelo de datos de errores comunes")
public class TipoErrorModel {
	
	@Schema(description = "Identificador único del tipo de error", example = "1")
	private Long id;
	
	@Schema(description = "Titulo del error", example = "Base de Datos")
	private String titulo;
	
	@Schema(description = "Descripción del tipo de error", example = "Analisis de la base de datos")
	private String descripcion;
	
	@Schema(description = "Código del tipo de error", example = "ERR001")
	private String codigo;
	
	@Tolerate
	protected TipoErrorModel() {
		
	}
	
	public static TipoErrorModel crearTipoErrorModel(TipoError tipoError) {
		if(Objects.nonNull(tipoError)) {
			return TipoErrorModel.builder()
					.id(tipoError.getId())
					.titulo(tipoError.getTitulo())
					.descripcion(tipoError.getDescripcion())
					.codigo(tipoError.getCodigo())
					.build();
		}
		return null;
	}

}
