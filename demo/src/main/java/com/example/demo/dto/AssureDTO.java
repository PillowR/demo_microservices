package com.example.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author cecile
 *
 */
public class AssureDTO {

	private String nom;    

	private String prenom;    

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;    

	private Long numeroAssure;    

	private String dossierMedical;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}

	public String getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(String dossierMedical) {
		this.dossierMedical = dossierMedical;
	}

	@Override
	public String toString() {
		return "AssureBean [nom=" + nom + ", prenom=" + prenom + ", numeroAssure=" + numeroAssure + ", dossierMedical="
				+ dossierMedical + "]";
	}  

}
