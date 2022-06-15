package com.verificador.catalogos.domain.repository;


import org.springframework.data.repository.CrudRepository;
import com.verificador.catalogos.domain.entity.CombustibleTanque;

public interface CombustibleTanqueRepository extends CrudRepository<CombustibleTanque, Long>{
	public CombustibleTanque findByTanqueId(Long id);
}
