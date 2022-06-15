package com.verificador.catalogos.facade;

import java.util.List;
import com.verificador.catalogos.web.model.GasolineraModel;

public interface EstacionFacade {
	
	public GasolineraModel findEstacionById(Long id);
	public List<GasolineraModel> findEstacionByIdMarca(Long id);
}
