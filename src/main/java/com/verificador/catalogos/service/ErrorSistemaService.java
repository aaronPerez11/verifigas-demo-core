package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verificador.catalogos.domain.entity.ErrorSistema;
import com.verificador.catalogos.domain.repository.ErrorSistemaRepository;
import com.verificador.catalogos.exception.ExceptionNotFound;
import com.verificador.catalogos.facade.ErrorSistemaFacade;
import static com.verificador.catalogos.utils.enums.TipoErrorEnum.CATALOGO_TIPOS_ERRORES_NO_ENCONTRADOS;
import com.verificador.catalogos.web.model.ErrorSistemaModel;

@Service
public class ErrorSistemaService implements ErrorSistemaFacade {

	private final ErrorSistemaRepository errorSistemaRepository;

	@Autowired
	public ErrorSistemaService(ErrorSistemaRepository errorSistemaRepository) {
		this.errorSistemaRepository = errorSistemaRepository;
	}

	@Override
	public List<ErrorSistemaModel> findByTipoErrorId(Long id) {
		List<ErrorSistema> listErrorSistema = errorSistemaRepository.findByTipoErrorId(id);
		if(listErrorSistema.isEmpty()) {
			throw new ExceptionNotFound(CATALOGO_TIPOS_ERRORES_NO_ENCONTRADOS.getError());
		}
		return listErrorSistema
				.stream()
				.map(ErrorSistemaModel::crearErrorSistemaModel)
				.collect(Collectors.toList());
	}

}
