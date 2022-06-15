package com.verificador.catalogos.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verificador.catalogos.domain.entity.Marca;
import com.verificador.catalogos.domain.repository.MarcaRepository;
import com.verificador.catalogos.exception.ExceptionNotFound;
import com.verificador.catalogos.facade.MarcaFacade;
import com.verificador.catalogos.utils.enums.TipoErrorEnum;
import com.verificador.catalogos.web.model.MarcaModel;
import static com.verificador.catalogos.web.model.MarcaModel.crearModeloMarca;


@Service
public class MarcaService implements MarcaFacade{
	
	private final MarcaRepository marcaRepository;
	
	@Autowired
	private MarcaService(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}
	
	/*
	 * Método encargado de listar todas las marcas gasolineras existentes
	 * @return List<MarcaModel>
	 * @exception ExceptionNotFound
	 */
	@Override
	public List<MarcaModel> findAll() {
		List<Marca> listaMarcas = (List<Marca>) marcaRepository.findAll();
		if(listaMarcas.isEmpty()) {
			 throw new ExceptionNotFound(TipoErrorEnum.MARCAS_NO_ENCONTRADAS.getError());
		}

		return listaMarcas
		.stream()
		.map(MarcaModel::crearModeloMarca)
		.collect(Collectors.toList());
				
	}

	/*
	 * Métod encargado de buscar por id una marca gasolinera
	 * @param id
	 * @return MarcaModel
	 * @exception ExceptionNotFound
	 */
	@Override
	public MarcaModel findById(Long id) {
		Marca marca = marcaRepository.findById(id).orElseThrow(() -> new ExceptionNotFound(TipoErrorEnum.MARCA_NO_ENCONTRADA.getError())); 
		return crearModeloMarca(marca);
	}

}
