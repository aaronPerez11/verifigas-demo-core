package com.verificador.catalogos.web.model;

import java.util.Objects;

import com.verificador.catalogos.domain.entity.Bomba;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder(toBuilder = true)
@Schema(name = "Estación Model", description = "Modelo de datos de la estación de servicio")
public class BombaModel {
	
	@Schema(description = "Identificador único de la estación de servicio", example = "1")
	private Long id;
	
	@Schema(description = "Tipo de combustible que entrega", example = "GASOLINA")
	private String combustible;
	
	@Schema(description = "Código asignado a la estación de servicio", example = "ESG-001")
	private String codigo;
	
	
	
	@Tolerate
	protected BombaModel() {
		
	}
	
	public static BombaModel crearTipoEstacionModel(Bomba tipoEstacion) {
		if(Objects.nonNull(tipoEstacion)) {
			return BombaModel.builder()
					.id(tipoEstacion.getId())
					.combustible(tipoEstacion.getCombustible().getCombustible())
					.codigo(tipoEstacion.getCodigo())
					.build();
		}
		return null;
	}

}
