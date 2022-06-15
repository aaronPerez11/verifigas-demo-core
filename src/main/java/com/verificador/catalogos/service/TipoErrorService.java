package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verificador.catalogos.domain.entity.TipoError;
import com.verificador.catalogos.domain.repository.TipoErrorRepository;
import com.verificador.catalogos.exception.ExceptionNotFound;
import com.verificador.catalogos.facade.TipoErrorFacade;
import com.verificador.catalogos.utils.enums.TipoErrorEnum;
import com.verificador.catalogos.web.model.TipoErrorModel;

@Service
public class TipoErrorService implements TipoErrorFacade{
	
	private final TipoErrorRepository tipoErrorRepository;
	
	@Autowired
	public TipoErrorService(TipoErrorRepository tipoErrorRepository) {
		this.tipoErrorRepository = tipoErrorRepository;
	}



	/*
	 * Método encargado de listar los tipos de errores del sistema
	 * @return List<TipoErrorModel>
	 * @exception ExceptionNotFound
	 * */
	@Override
	public List<TipoErrorModel> findAll() {
		List<TipoError> listaTipoErrores = (List<TipoError>) tipoErrorRepository.findAll();
		if(listaTipoErrores.isEmpty()) {
			throw new ExceptionNotFound(TipoErrorEnum.TIPOS_ERRORES_NO_ENCONTRADOS.getError());
		}
		return listaTipoErrores
				.stream()
				.map(TipoErrorModel::crearTipoErrorModel)
				.collect(Collectors.toList());
	}

}
