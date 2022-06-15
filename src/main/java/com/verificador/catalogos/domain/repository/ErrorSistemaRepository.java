package com.verificador.catalogos.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.verificador.catalogos.domain.entity.ErrorSistema;

public interface ErrorSistemaRepository extends CrudRepository<ErrorSistema, Long>{
	List<ErrorSistema> findByTipoErrorId(Long id);
}
