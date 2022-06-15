package com.verificador.catalogos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.verificador.catalogos.service.EstacionService;
import com.verificador.catalogos.service.MarcaService;
import com.verificador.catalogos.service.TanqueService;
import com.verificador.catalogos.service.BombaService;
import com.verificador.catalogos.web.model.GasolineraModel;
import com.verificador.catalogos.web.model.MarcaModel;
import com.verificador.catalogos.web.model.TanqueModel;
import com.verificador.catalogos.web.model.BombaModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_MARCAS;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_ID;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_GASOLINERAS;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_BOMBAS;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_GASOLINERA_ID;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_TANQUES;
import static com.verificador.catalogos.utils.Constantes.CODE_SUCCESFULL;
import static com.verificador.catalogos.utils.Constantes.CODE_BAD_REQUEST;
import static com.verificador.catalogos.utils.Constantes.CODE_NOT_FOUND;
import static com.verificador.catalogos.utils.Constantes.CROSSORIGIN;

import java.util.List;

@RestController
@CrossOrigin(origins = CROSSORIGIN)
@RequestMapping(value = CONTEXT_MARCAS)
public class MarcaController {
	
	private final MarcaService marcasService;
	private final EstacionService estacionService;
	private final BombaService bombaService;
	private final TanqueService tanqueService;
	
	@Autowired
	private MarcaController(MarcaService marcasService,
			EstacionService estacionService,
			BombaService bombaService,
			TanqueService tanqueService) {
		this.marcasService = marcasService;
		this.estacionService = estacionService;
		this.bombaService = bombaService;
		this.tanqueService = tanqueService;
	}
	
	@Operation(summary = "Consulta de gasolineras", description = "Consulta marcas gasolineras disponibles", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Marcas de gasolineras listadas exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontraron marcas de gasolineras")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MarcaModel>> consultarMarcas(){
		return ResponseEntity.status(HttpStatus.OK).body(marcasService.findAll());
	}
	
	@Operation(summary = "Consulta de gasolinera por ID", description = "Consulta una marca de gasolinera por ID", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Marca de gasolinera consultada exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontro la marca gasolinera")
	},
			parameters = {
					@Parameter(name = "id", description = "Identificador único", in = ParameterIn.PATH, example = "1")
			})
	@GetMapping(value = CONTEXT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MarcaModel> consultarMarca(@PathVariable(required = true) Long id){
		return ResponseEntity.status(HttpStatus.OK).body(marcasService.findById(id));
	}
	
	@Operation(summary = "Consulta de estaciones gasolineras de una marca", description = "Consulta las estaciones gasolineras de una marca por ID", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Estaciones gasolineras consultadas exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontraron estaciones gasolineras de la marca indicada")
	},
			parameters = {
					@Parameter(name = "id", description = "Identificador único", in = ParameterIn.PATH, example = "1")
			})
	@GetMapping(value = CONTEXT_GASOLINERAS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GasolineraModel>> consultarEstaciones(@PathVariable(required = true) Long id){
		return ResponseEntity.status(HttpStatus.OK).body(estacionService.findEstacionByIdMarca(id));
	}
	
	@Operation(summary = "Consulta de estación gasolinera", description = "Consulta de estación gasolinera de una marca en específico por ID", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Estación gasolinera consultada exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontro la estación gasolinera de la marca indicada")
	},
			parameters = {
					@Parameter(name = "id", description = "Identificador único", in = ParameterIn.PATH, example = "1"),
					@Parameter(name = "idGasolinera", description = "Identificador único", in = ParameterIn.PATH, example = "1")
			})
	@GetMapping(value = CONTEXT_GASOLINERA_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GasolineraModel> consultarEstacion(@PathVariable(required = true) Long id, @PathVariable(required = true) Long idGasolinera){
		return ResponseEntity.status(HttpStatus.OK).body(estacionService.findEstacionIdMarcaIdEstacion(id, idGasolinera));
	}
	
	@Operation(summary = "Consulta de estaciónes gasolineras", description = "Consulta de estaciónes de servicio de una gasolinera en específica", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Estaciones de servicio consultadas exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontro la estación de servicio de la marca indicada")
	},
			parameters = {
					@Parameter(name = "id", description = "Identificador único", in = ParameterIn.PATH, example = "1"),
					@Parameter(name = "idGasolinera", description = "Identificador único", in = ParameterIn.PATH, example = "1")
			})
	@GetMapping(value = CONTEXT_BOMBAS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BombaModel>> consultarTipoEstaciones(@PathVariable(required = true) Long id, @PathVariable(required = true) Long idGasolinera){
		return ResponseEntity.status(HttpStatus.OK).body(bombaService.findTipoEstacionByIdEstacion(id,idGasolinera));
	}
	
	@Operation(summary = "Consulta de tanques de una gasolinera", description = "Consulta los tanques de una gasolinera de una marca en específica", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Tanques consultados exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontro la estación de servicio de la marca indicada")
	},
			parameters = {
					@Parameter(name = "id", description = "Identificador único", in = ParameterIn.PATH, example = "1"),
					@Parameter(name = "idGasolinera", description = "Identificador único", in = ParameterIn.PATH, example = "1")
			})
	@GetMapping(value = CONTEXT_TANQUES, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TanqueModel>> consultarTanques(@PathVariable(required = true) Long id, @PathVariable(required = true) Long idGasolinera){
		return ResponseEntity.status(HttpStatus.OK).body(tanqueService.findTanqueByIdMarcaByIdGasolinera(id, idGasolinera));
	}
}

