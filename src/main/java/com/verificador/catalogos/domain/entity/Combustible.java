package com.verificador.catalogos.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "combustible", schema = "public", indexes = {
		@Index(name = "id_combustible_idx", columnList = "id_combustible") })
@Getter
public class Combustible implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_combustible")
	private Long id;

	@Column(name = "tipo_combustible")
	private String combustible;
	
	@Column(name = "codigo")
	private String codigo;
}
