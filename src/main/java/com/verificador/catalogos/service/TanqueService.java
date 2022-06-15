package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verificador.catalogos.domain.entity.Tanque;
import com.verificador.catalogos.domain.repository.CombustibleTanqueRepository;
import com.verificador.catalogos.domain.repository.TanqueRepository;
import com.verificador.catalogos.exception.ExceptionNotFound;
import com.verificador.catalogos.facade.TanqueFacade;
import com.verificador.catalogos.utils.enums.TipoErrorEnum;
import com.verificador.catalogos.web.model.GasolineraModel;
import com.verificador.catalogos.web.model.MarcaModel;
import com.verificador.catalogos.web.model.TanqueModel;

@Service
public class TanqueService implements TanqueFacade{
	
	
	private final TanqueRepository tanqueRepository;
	private final CombustibleTanqueRepository combustibleTanqueRepository;
	private final EstacionService estacionService;
	private final MarcaService marcaService;
	
	@Autowired
	public TanqueService(TanqueRepository tanqueRepository, 
			CombustibleTanqueRepository combustibleTanqueRepository,
			EstacionService estacionService, 
			MarcaService marcaService) {
		this.estacionService = estacionService;
		this.marcaService = marcaService;
		this.tanqueRepository = tanqueRepository;
		this.combustibleTanqueRepository = combustibleTanqueRepository;
	}



	/**
	 * Método encargado de listar los tanques de una gasolinera
	 * @param id marca, id gasolinera
	 * @return List<TanqueModel>
	 * @exception ExceptionNotFound
	 */
	@Override
	public List<TanqueModel> findTanqueByIdMarcaByIdGasolinera(Long id, Long idGasolinera) {
		MarcaModel buscarMarca =  marcaService.findById(id);
		GasolineraModel buscarEstacion = estacionService.findEstacionById(idGasolinera);
		
		if(!buscarMarca.getNombre().equalsIgnoreCase(buscarEstacion.getNombre())) {
			throw new ExceptionNotFound(TipoErrorEnum.ESTACIONES_NO_ENCONTRADA.getError());
		}
		
		List<Tanque> consultaTanques = tanqueRepository.findByGasolineraId(idGasolinera);
		if(consultaTanques.isEmpty()) {
			throw new ExceptionNotFound(TipoErrorEnum.TANQUE_NO_ENCONTRADO.getError());
		}
		
		
		return consultaTanques
				.stream()
				.map(tanque -> {
					String combustibletanque = combustibleTanqueRepository.findByTanqueId(tanque.getId()).getCombustible().getCombustible();
					return TanqueModel
							.builder()
							.id(tanque.getId())
							.idGasolinera(tanque.getGasolinera().getId())
							.tipoCombustible(combustibletanque)
							.capacidad(tanque.getCapacidad())
							.presion(tanque.getPresion())
							.temperatura(tanque.getTemperatura())
							.build();
				})
				.collect(Collectors.toList());
	}

}
