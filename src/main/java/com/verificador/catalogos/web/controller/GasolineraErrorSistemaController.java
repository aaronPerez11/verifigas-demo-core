package com.verificador.catalogos.web.controller;

import static com.verificador.catalogos.utils.Constantes.CODE_BAD_REQUEST;
import static com.verificador.catalogos.utils.Constantes.CODE_NOT_FOUND;
import static com.verificador.catalogos.utils.Constantes.CODE_SUCCESFULL;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_CATALOGO_ERRORES;
import static com.verificador.catalogos.utils.Constantes.CROSSORIGIN;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_ID;
import static com.verificador.catalogos.utils.Constantes.CONTEXT_GASOLINERA_PDF;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.verificador.catalogos.service.GasolineraErrorSistemaService;
import com.verificador.catalogos.service.PdfService;
import com.verificador.catalogos.web.model.GasolineraErrorSistemaModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@CrossOrigin(origins = CROSSORIGIN)
@RequestMapping(value = CONTEXT_CATALOGO_ERRORES)
public class GasolineraErrorSistemaController {
	
	private final GasolineraErrorSistemaService gasolineraErrorSistemaService;
	private final PdfService pdfService;
	
	@Autowired
	public GasolineraErrorSistemaController(
			GasolineraErrorSistemaService gasolineraErrorSistemaService,
			PdfService pdfService) {
		this.gasolineraErrorSistemaService = gasolineraErrorSistemaService;
		this.pdfService = pdfService;
	}
	
	@Operation(summary = "Consulta catalogo de errores", description = "Consulta catalogo de errores", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Catalogo de errores consultados exitosamente"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontraron catalogos de errores")
	}, parameters = {
			@Parameter(name = "id", description = "Identificador único de la gasolinera", in = ParameterIn.PATH, example = "1")
	})
	@GetMapping(value = CONTEXT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GasolineraErrorSistemaModel>> consultaCatalogoError(@PathVariable(required = true) Long id){
		return ResponseEntity.status(HttpStatus.OK).body(gasolineraErrorSistemaService.GasolineraErrorSistema(id));
	}
	
	@Operation(summary = "Creación de PDF con los errores de una gasolinera", description = "Creación de PDF", responses = {
			@ApiResponse(responseCode = CODE_SUCCESFULL, description = "Creación de PDF"),
			@ApiResponse(responseCode = CODE_BAD_REQUEST, description = "Petición incorrecta"),
			@ApiResponse(responseCode = CODE_NOT_FOUND, description = "No se encontro el PDF creado")
	}, parameters = {
			@Parameter(name = "id", description = "Identificador único de la gasolinera", in = ParameterIn.PATH, example = "1")
	})
	@PostMapping(value = CONTEXT_GASOLINERA_PDF, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> generarPDF(HttpServletResponse response, @PathVariable(required = true) Long id) throws IOException{	
		pdfService.crearPDF(id, response);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
