package com.verificador.catalogos.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.verificador.catalogos.domain.entity.Tanque;

public interface TanqueRepository extends CrudRepository<Tanque, Long>{
	List<Tanque> findByGasolineraId(Long id);
}
