package com.verificador.catalogos.facade;

import java.util.List;

import com.verificador.catalogos.web.model.TipoErrorModel;

public interface TipoErrorFacade {

	public List<TipoErrorModel> findAll();
}
