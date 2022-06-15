package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verificador.catalogos.domain.entity.GasolineraErrorSistema;
import com.verificador.catalogos.domain.repository.GasolineraErrorSistemaRepository;
import com.verificador.catalogos.exception.ExceptionNotFound;
import com.verificador.catalogos.facade.GasolineraErrorSistemaFacade;
import static com.verificador.catalogos.utils.enums.TipoErrorEnum.CATALOGO_ERRORES_NO_ENCONTRADO;
import com.verificador.catalogos.web.model.GasolineraErrorSistemaModel;

@Service
public class GasolineraErrorSistemaService implements GasolineraErrorSistemaFacade {
	
	private final GasolineraErrorSistemaRepository gasolineraErrorSistemaRepository;
	
	@Autowired
	public GasolineraErrorSistemaService(GasolineraErrorSistemaRepository gasolineraErrorSistemaRepository) {
		this.gasolineraErrorSistemaRepository = gasolineraErrorSistemaRepository;
	}

	@Override
	public List<GasolineraErrorSistemaModel> GasolineraErrorSistema(Long id) {
		List<GasolineraErrorSistema> consultaGasolinerErrorSistema = gasolineraErrorSistemaRepository.findByGasolineraId(id);
		if(consultaGasolinerErrorSistema.isEmpty()) {
			throw new ExceptionNotFound(CATALOGO_ERRORES_NO_ENCONTRADO.getError());
		}
		return consultaGasolinerErrorSistema
				.stream()
				.map(GasolineraErrorSistemaModel::creaGasolineraErrorSistemaModel)
				.collect(Collectors.toList());
	}



}
