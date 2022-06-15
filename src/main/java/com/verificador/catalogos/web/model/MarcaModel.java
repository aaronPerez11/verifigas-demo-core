package com.verificador.catalogos.web.model;


import java.util.Objects;
import com.verificador.catalogos.domain.entity.Marca;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;


@Builder(toBuilder = true)
@Getter
@Schema(name = "MarcaModel", description = "Modelo de datos de marcas gasolineras")
public class MarcaModel {
	
	@Schema(description = "Identificador único de la gasolinera.", example = "1")
	private Long id;
	
	@Schema(description = "Nombre de la gasolinera.", example = "Pemex")
	private String nombre;
	
	@Schema(description = "Descripción de la gasolinera.", example = "Gasolinera propiedad de Pemex")
	private String descripcion;
	
	@Schema(description = "Caratula de gasolinera", example = "pemex.jgp")
	private String imagen;

	@Tolerate
	protected MarcaModel() {
		
	}
	
	public static MarcaModel crearModeloMarca(Marca marca) {
		if(Objects.nonNull(marca)) {
			return MarcaModel.builder()
					.id(marca.getId())
					.nombre(marca.getNombre())
					.descripcion(marca.getDescripcion())
					.imagen(marca.getImagen())
					.build();	
		}
		return null;
	}

}
