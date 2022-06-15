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
@Table(name = "tanque", schema = "public",
indexes = {
		@Index(name = "id_tanque_idx", columnList = "id_tanque")
})
@Getter
public class Tanque implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tanque")
	private long id;
	
	@Fetch(FetchMode.JOIN)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gasolinera", referencedColumnName = "id_gasolinera")
	private Gasolinera gasolinera;
		
	@Column(name = "capacidad")
	private double capacidad;
	
	@Column(name = "presion")
	private double presion;
	
	@Column(name = "temperatura")
	private double temperatura;
	
}
