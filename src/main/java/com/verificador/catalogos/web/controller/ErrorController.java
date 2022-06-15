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
import com.verificador.catalogos.service.ErrorSistemaService;
import com.verificador.catalogos.service.TipoErrorService;
import com.verificador.catalogos.web.model.ErrorSistemaModel;
import com.verificador.catalogos.web.model.TipoErrorModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import static com.verificador.catalogos.utils.Constantes.CROSSORIGIN;
import java.util.List;
import static com.verificador.catalogos.utils.Constantes.CODE_BAD_REQUEST;
import static com.verificador.catalogos.utils.Constantes.CODE_NOT_FOUND;
import static com.verificador.catalogos.utils.Constantes.CODE_SUCCESFULL;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_ERRORES;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_ERRORES_SISTEMA;


@RestController
@CrossOrigin(origins = CROSSORIGIN)
@RequestMapping(value = CONTEXT_ERRORES)
public class ErrorController {
	
	private final TipoErrorService tipoErrorService;
	private final ErrorSistemaService errorSistemaService;
	
	@Autowired
	public ErrorController(TipoErrorService tipoErrorService,
			ErrorSistemaService errorSistemaService) {
		this.tipoErrorService = tipoErrorService;
		this.errorSistemaService = errorSistemaService;
	}
	
	
	@Operation(summary = "Consulta de tipos de errores del sistema", description = "Consulta de tipos de errores del sistema", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Tipos de errores consultados exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontraron los tipos de errores")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoErrorModel>> consultaTipoErrores(){
		return ResponseEntity.status(HttpStatus.OK).body(tipoErrorService.findAll());
	}
	
	@Operation(summary = "Consulta catalogo de tipos de errores", description = "Consulta catalogo de tipos de errores", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Catalogo de Tipos de errores consultados exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontraron catalogos de tipos de errores")
	}, parameters = {
			@Parameter(name = "id", description = "Identificador del tipo de error", in = ParameterIn.PATH, example = "1")
	})
	@GetMapping(value = CONTEXT_ERRORES_SISTEMA, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ErrorSistemaModel>> consultaCatalogoTipoErrores(@PathVariable(required = true) Long id){
		return ResponseEntity.status(HttpStatus.OK).body(errorSistemaService.findByTipoErrorId(id));
	}
	
}
