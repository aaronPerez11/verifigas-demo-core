package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verificador.catalogos.domain.entity.Bomba;
import com.verificador.catalogos.domain.repository.BombaRepository;
import com.verificador.catalogos.exception.ExceptionBadRequest;
import com.verificador.catalogos.facade.BombaFacade;
import com.verificador.catalogos.web.model.GasolineraModel;
import com.verificador.catalogos.web.model.BombaModel;
import static com.verificador.catalogos.utils.enums.TipoErrorEnum.ESTACIONES_NO_ENCONTRADA;

@Service
public class BombaService implements BombaFacade{

	private final BombaRepository bombaRepository;
	private final EstacionService estacionService;

	@Autowired
	public BombaService(BombaRepository bombaRepository,
			MarcaService marcaService,
			EstacionService estacionService) {
		this.bombaRepository = bombaRepository;
		this.estacionService = estacionService;
	}

	@Override
	public List<BombaModel> findTipoEstacionByIdEstacion(Long id, Long idGasolinera) {
		
		GasolineraModel estacion = estacionService.findEstacionIdMarcaIdEstacion(id, idGasolinera);
		List<Bomba> consultarTipoEstaciones = bombaRepository.findByGasolineraId(estacion.getId());

		if(consultarTipoEstaciones.isEmpty()) {
			throw new ExceptionBadRequest(ESTACIONES_NO_ENCONTRADA.getError());
		}
		
		return consultarTipoEstaciones
				.stream()
				.map(BombaModel::crearTipoEstacionModel)
				.collect(Collectors.toList());
	}
	
	
}
