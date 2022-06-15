package com.verificador.catalogos.facade;

import java.util.List;
import com.verificador.catalogos.web.model.TanqueModel;

public interface TanqueFacade {
	public List<TanqueModel> findTanqueByIdMarcaByIdGasolinera(Long id, Long idGasolinera); 

}
