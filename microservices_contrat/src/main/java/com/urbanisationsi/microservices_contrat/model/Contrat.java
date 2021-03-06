package com.urbanisationsi.microservices_contrat.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Contrat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebut;

	private Long numeroContrat;

	private Long numeroAssure;

	private Long numeroProduit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Long getNumeroContrat() {
		return numeroContrat;
	}

	public void setNumeroContrat(Long numeroContrat) {
		this.numeroContrat = numeroContrat;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}

	public Long getNumeroProduit() {
		return numeroProduit;
	}

	public void setNumeroProduit(Long numeroProduit) {
		this.numeroProduit = numeroProduit;
	}

	public Contrat(Integer id, LocalDate dateDebut, Long numeroContrat, Long numeroAssure, Long numeroProduit) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.numeroContrat = numeroContrat;
		this.numeroAssure = numeroAssure;
		this.numeroProduit = numeroProduit;
	}

	public Contrat() {
		super();
	}

}
