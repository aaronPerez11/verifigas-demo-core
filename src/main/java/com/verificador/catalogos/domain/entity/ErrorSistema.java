package com.verificador.catalogos.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;

@Entity
@Table(name = "error_sistema", schema = "public", indexes = {
		@Index(name = "id_error_sistema_idx", columnList = "id_error_sistema") })
@Getter
public class ErrorSistema implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_error_sistema")
	private Long id;
	
	@Fetch(FetchMode.JOIN)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_error", referencedColumnName = "id_tipo_error")
	private TipoError tipoError;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
}
