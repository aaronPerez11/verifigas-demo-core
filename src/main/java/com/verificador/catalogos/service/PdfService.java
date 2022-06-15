package com.verificador.catalogos.service;

import static com.verificador.catalogos.utils.Utilidades.formatoFecha;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;
import com.verificador.catalogos.web.model.GasolineraErrorSistemaModel;
import com.verificador.catalogos.web.model.TipoErrorModel;

@Service
public class PdfService {
	
	private final TipoErrorService tipoErrorService;
	private final GasolineraErrorSistemaService gasolineraErrorSistemaService;
	
	@Autowired
	public PdfService(TipoErrorService tipoErrorService, 
			ErrorSistemaService errorSistemaService,
			GasolineraErrorSistemaService gasolineraErrorSistemaService) {
		this.tipoErrorService = tipoErrorService;
		this.gasolineraErrorSistemaService = gasolineraErrorSistemaService;
	}
	
	
	public void crearPDF(Long idGasolinera,HttpServletResponse response)throws IOException {
		List<TipoErrorModel> listaTiposErrores = tipoErrorService.findAll();
		List<GasolineraErrorSistemaModel> erroresGasolinera = gasolineraErrorSistemaService.GasolineraErrorSistema(idGasolinera);
		
		asignarHeaders(response);
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		Font font = new Font(Font.HELVETICA,12);
		
		document.open();
		listaTiposErrores.forEach(tipo -> {
					
			Table table = new Table(3);
			table.setPadding(5);
		    table.setWidth(100);
					
			Cell cell = new Cell(tipo.getTitulo());
			cell.setHeader(true);
			cell.setVerticalAlignment(VerticalAlignment.CENTER);
		    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
		    cell.setColspan(3);
		    table.addCell(cell);
		    
		    table.addCell(new Phrase("Estatus", font));
		    table.addCell(new Phrase("Titulo", font));
		    table.addCell(new Phrase("Descripción", font));
		    table.endHeaders();
		    
		   
		    this.complementarPDF(erroresGasolinera, tipo.getId(), table, document);
			document.add(table);
			
		   
		});
		document.close();
	}
	
	/**
	 * Método encargado de añadir mas elementos al PDF
	 * @param erroresGasolinera
	 * @param idError
	 * @param table
	 * @param documento
	 */
	private void complementarPDF(List<GasolineraErrorSistemaModel> erroresGasolinera, Long idError, Table table, Document documento) {
		List<GasolineraErrorSistemaModel> erroresFiltrados = erroresGasolinera.stream().filter(error -> 
		error.getErrorSistemaModel().getIdTipoError().equals(idError)).toList();
		
		erroresFiltrados.forEach(e -> {				
				table.addCell(identificarEstatus(e.isCorrecto()));
				table.addCell(e.getErrorSistemaModel().getTitulo());
				table.addCell(e.getErrorSistemaModel().getDescripcion());
		});
	}
	
	/**
	 * Método encargado de asignar los headers necesarios para descargar PDF
	 * @param response
	 */
	private void asignarHeaders(HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + formatoFecha() + ".pdf";
        response.setHeader(headerKey, headerValue);
	}
	
	/**
	 * Método encargado de identificar el estatus del error
	 * @param estatus
	 * @return estatus en texto
	 */
	private String identificarEstatus(boolean estatus) {
		String valorFinal = "incorrecto";
		if(estatus) {
			return valorFinal = "correcto";
		}
		return valorFinal;
	}
}
