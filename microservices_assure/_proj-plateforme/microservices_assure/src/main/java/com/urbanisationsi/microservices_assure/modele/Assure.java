package com.urbanisationsi.microservices_assure.modele;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Assure extends Personne {

	private String dossierMedical;
	private Long numeroAssure;

	public String getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(String dossierMedical) {
		this.dossierMedical = dossierMedical;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}

	public Assure() {
		super();
	}

	public Assure(Integer id, String nom, String prenom, Long numeroPersonne, LocalDate dateNaissance,
			Long numeroAssure, String dossierMedical) {
		super(id, nom, prenom, numeroPersonne, dateNaissance);
		this.numeroAssure = numeroAssure;
		this.dossierMedical = dossierMedical;
	}

	public Assure(Integer id, String nom, String prenom, Long numeroPersonne, LocalDate dateNaissance) {
		super(id, nom, prenom, numeroPersonne, dateNaissance);
	}

}
