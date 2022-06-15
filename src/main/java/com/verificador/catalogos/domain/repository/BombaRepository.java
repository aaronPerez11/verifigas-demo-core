package com.verificador.catalogos.domain.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.verificador.catalogos.domain.entity.Bomba;


public interface BombaRepository extends CrudRepository<Bomba, Long>{

	List<Bomba> findByGasolineraId(Long id);
}
