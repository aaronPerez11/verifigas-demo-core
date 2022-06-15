package com.verificador.catalogos.domain.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.verificador.catalogos.domain.entity.Gasolinera;

public interface EstacionRepository extends CrudRepository<Gasolinera, Long>{

	List<Gasolinera> findByMarcaId(Long id);
}
