package com.verificador.catalogos.web.model;

import java.util.Objects;

import com.verificador.catalogos.domain.entity.Tanque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Builder(toBuilder = true)
@Schema(name = "Tanque Model", description = "Modelo de datos de tanque de una estación")
public class TanqueModel {
	
	@Schema(description = "Identificador único del tanque contenedor de combustible", example = "1")
	private Long id;
	
	@Schema(description = "Identidicador único de la gasolinera", example = "1")
	private Long idGasolinera;
	
	@Setter
	@Schema(description = "Tipo de combustible que almacena el tanque", example = "GASOLINA MAGNA")
	private String tipoCombustible;
	
	@Schema(description = "Capacidad de almacenamiento de combustible del tanque", example = "80,000")
	private double capacidad;
	
	@Schema(description = "Presión del tanque", example = "101.325")
	private double presion;
	
	@Schema(description = "Temperatura del tanque", example = "20.45")
	private double temperatura;
	
	@Tolerate
	protected TanqueModel() {}
	
	public static TanqueModel crearTanqueModel(Tanque tanque) {
		if(Objects.nonNull(tanque)) {
			return TanqueModel.builder()
					.id(tanque.getId())
					.idGasolinera(tanque.getGasolinera().getId())
					.capacidad(tanque.getCapacidad())
					.presion(tanque.getPresion())
					.temperatura(tanque.getTemperatura())
					.build();
		}
		return null;
	}
}
