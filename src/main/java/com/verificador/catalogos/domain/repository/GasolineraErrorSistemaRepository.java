package com.verificador.catalogos.domain.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.verificador.catalogos.domain.entity.GasolineraErrorSistema;

public interface GasolineraErrorSistemaRepository extends CrudRepository<GasolineraErrorSistema, Long>{
	List<GasolineraErrorSistema> findByGasolineraId(Long id);
}
