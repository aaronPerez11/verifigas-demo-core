package com.verificador.catalogos.facade;

import java.util.List;
import com.verificador.catalogos.web.model.MarcaModel;

public interface MarcaFacade {

	public List<MarcaModel> findAll();
	public MarcaModel findById(Long id);
}
