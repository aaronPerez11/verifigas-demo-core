package com.verificador.catalogos.web.model;

import java.util.Objects;

import com.verificador.catalogos.domain.entity.Gasolinera;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder(toBuilder = true)
@Schema(name = "Gasolinera Model", description = "Modelo de datos de estación gasolinera")
public class GasolineraModel {

	@Schema(description = "Identificador único de la estación gasolinera", example = "1")
	private Long id;
	
	@Schema(description = "Nombre de la marca gasolinera", example = "Petro Seven")
	private String nombre;
	
	@Schema(description = "Dirección de la estación gasolinera", example = "Arturo B. de la Garza esq, Jorge González Camarena s/n, El Roble, 66414 San Nicolás de los Garza, N.L.")
	private String direccion;

	@Schema(description = "Caratula de gasolinera", example = "pemex.jgp")
	private String imagen;
	
	@Schema(description = "Nombre del fabricante del sistema volumétrico", example = "eGas")
	private String fabricante;
	
	@Schema(description = "Versión del sistema volumétrico", example = "10.4.5")
	private String version;
	
	@Schema(description = "Tipo de conectividad del sistema volumétrico", example = "USB")
	private String conectividad;

	
	@Tolerate
	protected GasolineraModel() {
		
	}
	
	public static GasolineraModel crearEstacionModel(Gasolinera gasolinera) {
		if(Objects.nonNull(gasolinera)) {
			return GasolineraModel.builder()
					.id(gasolinera.getId())
					.nombre(gasolinera.getMarca().getNombre())
					.direccion(gasolinera.getDireccion())				
					.imagen(gasolinera.getMarca().getImagen())
					.fabricante(gasolinera.getFabricante())
					.version(gasolinera.getVersion())
					.conectividad(gasolinera.getConectividad())
					.build();
		}
		return null;
	}
}
