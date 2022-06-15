package com.verificador.catalogos.facade;

import java.util.List;
import com.verificador.catalogos.web.model.ErrorSistemaModel;

public interface ErrorSistemaFacade {
	List<ErrorSistemaModel> findByTipoErrorId(Long id);

}
