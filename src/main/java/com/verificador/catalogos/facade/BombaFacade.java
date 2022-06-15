package com.verificador.catalogos.facade;

import java.util.List;
import com.verificador.catalogos.web.model.BombaModel;

public interface BombaFacade {
	public List<BombaModel> findTipoEstacionByIdEstacion(Long id, Long idGasolinera);
}
