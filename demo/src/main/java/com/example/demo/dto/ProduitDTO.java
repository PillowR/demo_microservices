package com.example.demo.dto;

/**
 * @author cecile
 *
 */
public class ProduitDTO {

	private String libelle;

	private Long numeroProduit;

	public ProduitDTO(String libelle, Long numeroProduit) {
		super();
		this.libelle = libelle;
		this.numeroProduit = numeroProduit;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Long getNumeroProduit() {
		return numeroProduit;
	}

	public void setNumeroProduit(Long numeroProduit) {
		this.numeroProduit = numeroProduit;
	}

}
