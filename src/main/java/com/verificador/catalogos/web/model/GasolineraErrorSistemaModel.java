package com.verificador.catalogos.web.model;

import java.util.Objects;
import com.verificador.catalogos.domain.entity.GasolineraErrorSistema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder(toBuilder = true)
@Schema(name = "Gasolinera Error", description = "Modelo de datos de gasolinera error service")
public class GasolineraErrorSistemaModel {
	
	@Schema(description = "Identificador único del catalogo de error", example = "1")
	private Long id;
	
	@Schema(description = "Identificador único de la gasolinera", example = "1")
	private Long idGasolinera;
	
	@Schema(description = "Identificador único del tipo de error")
	private ErrorSistemaModel errorSistemaModel;
	
	@Schema(description = "Si cumple con las reglas o no", example = "true")
	private boolean correcto;
	
	@Schema(description = "Disponibilidad del tipo de error", example = "true")
	private boolean estatus;
	
	@Tolerate
	protected GasolineraErrorSistemaModel() {
		
	}
	
	public static GasolineraErrorSistemaModel creaGasolineraErrorSistemaModel(GasolineraErrorSistema errorSistema) {
		if(Objects.nonNull(errorSistema)) {
			return GasolineraErrorSistemaModel.builder()
					.id(errorSistema.getId())
					.idGasolinera(errorSistema.getGasolinera().getId())
					.errorSistemaModel(ErrorSistemaModel.crearErrorSistemaModel(errorSistema.getErrorSistema()))
					.correcto(errorSistema.isCorrecto())
					.estatus(errorSistema.isEstatus())
					.build();
		}
		return null;
	}

}
