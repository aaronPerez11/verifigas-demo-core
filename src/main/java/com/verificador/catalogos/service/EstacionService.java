package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verificador.catalogos.domain.entity.Gasolinera;
import com.verificador.catalogos.domain.repository.EstacionRepository;
import com.verificador.catalogos.exception.ExceptionNotFound;
import com.verificador.catalogos.facade.EstacionFacade;
import com.verificador.catalogos.utils.enums.TipoErrorEnum;
import com.verificador.catalogos.web.model.GasolineraModel;
import com.verificador.catalogos.web.model.MarcaModel;
import static com.verificador.catalogos.web.model.GasolineraModel.crearEstacionModel;

@Service
public class EstacionService implements EstacionFacade {
	
	private final EstacionRepository estacionRepository;
	private final MarcaService marcaService;
	
	@Autowired
	public EstacionService(EstacionRepository estacionRepository,
			MarcaService marcaService) {
		this.estacionRepository = estacionRepository;
		this.marcaService = marcaService;
	}


	/*
	 * Método encargado de listar las gasolineras de una marca en especifico
	 * @param id marca gasolinera
	 * @return List<EstacionModel>
	 * @exception ExceptionNotFound
	 */
	@Override
	public List<GasolineraModel> findEstacionByIdMarca(Long id) {
		List<Gasolinera> consultarEstaciones = estacionRepository.findByMarcaId(id);
		if(consultarEstaciones.isEmpty()) {
			throw new ExceptionNotFound(TipoErrorEnum.GASOLINERAS_NO_ENCONTRADAS.getError());
		}
		
		return consultarEstaciones
				.stream()
				.map(GasolineraModel::crearEstacionModel)
				.collect(Collectors.toList());
	}

	/*
	 * Método encargado de buscar una estación por id
	 * @param id estacion
	 * @return EstacionModel
	 * @exception ExceptionNotFound
	 */
	@Override
	public GasolineraModel findEstacionById(Long id) {
		Gasolinera estacionEntity = estacionRepository.findById(id)
				.orElseThrow(() -> new ExceptionNotFound(TipoErrorEnum.ESTACION_NO_ENCONTRADA.getError()));
		return crearEstacionModel(estacionEntity);
	}

	
	/*
	 * Método encargado de buscar una gasolinera de una marca por id
	 * @param id marca gasolinera, id gasolinera
	 * @return  EstacionModel
	 * @exception ExceptionNotFound
	 */
	public GasolineraModel findEstacionIdMarcaIdEstacion(Long id, Long idGasolinera) {
		
		MarcaModel marca = marcaService.findById(id);
		GasolineraModel gasolinera = findEstacionById(idGasolinera);
		
		if(!marca.getNombre().equalsIgnoreCase(gasolinera.getNombre())) {
			throw new ExceptionNotFound(TipoErrorEnum.ESTACIONES_NO_ENCONTRADA.getError());
		}
		
		return gasolinera;
	}
}
