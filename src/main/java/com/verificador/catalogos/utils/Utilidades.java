package com.verificador.catalogos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import static com.verificador.catalogos.utils.Constantes.DATE_FORMAT;

public interface Utilidades {
	
	static String formatoFecha() {
		return new SimpleDateFormat(DATE_FORMAT).format(new Date());		
	}

}
