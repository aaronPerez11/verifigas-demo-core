package com.verificador.catalogos.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.Getter;

@Entity
@Table(name = "estacion_tanque", schema = "public")
@Getter
public class EstacionTanque implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estacion_tanque")
	private Long id;
	
	@Fetch(FetchMode.JOIN)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bomba", referencedColumnName = "id_bomba")
	private Bomba estacion;
	
	@Fetch(FetchMode.JOIN)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tanque", referencedColumnName = "id_tanque")
	private Tanque tanque;

}
