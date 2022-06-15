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
@Table(name = "gasolinera", schema = "public", indexes = {
		@Index(name = "id_gasolinera_idx", columnList = "id_gasolinera")
})
@Getter
public class Gasolinera implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gasolinera")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
	private Marca marca;
			
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "fabricante")
	private String fabricante;
	
	@Column(name = "version")
	private String version;
	
	@Column(name = "conectividad")
	private String conectividad;

}
