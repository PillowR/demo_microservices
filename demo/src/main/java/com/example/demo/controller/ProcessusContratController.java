package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.AssureDTO;
import com.example.demo.dto.ContratDTO;
import com.example.demo.dto.ProduitDTO;
import com.example.demo.proxies.AssureProxy;
import com.example.demo.proxies.ContratProxy;
import com.example.demo.proxies.ProduitProxy;

@Controller
public class ProcessusContratController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AssureProxy assureProxy;

	@Autowired
	private ProduitProxy produitProxy;

	@Autowired
	private ContratProxy contratProxy;

	@GetMapping("/")
	public String accueil(Model model) {
		log.info("************ méthode accueil ************");
		AssureDTO assureDTO = new AssureDTO();
		model.addAttribute("cle_assure", assureDTO);
		return "Accueil";
	}

	@PostMapping(value = "/saisir-assure")
	public String saisirAssure(Model model, AssureDTO assure) {
		log.info("************ envoie requete vers saisirAssure ************");
		List<AssureDTO> assures = assureProxy.getAssureNomPrenom(assure.getNom(), assure.getPrenom());
		model.addAttribute("cle_liste_assure", assures);
		return "listAssures";
	}

	@GetMapping("/creerContrat/{numeroAssure}")
	public String choisirProduit(Model model, @PathVariable Long numeroAssure) {
		log.info("************ méthode choisir un produit ************");

		List<AssureDTO> assures;
		assures = assureProxy.getAssureNumeroAssure(numeroAssure);
		AssureDTO assureDTO = assures.get(0);
		model.addAttribute("assure", assureDTO);

		List<ProduitDTO> produits = produitProxy.getAllProduits();
		model.addAttribute("produits", produits);

		return "listProduits";
	}

	@GetMapping(path = "/affecterNumeroProduit/{numeroAssure}/{numeroProduit}")
	public String affecterNumeroProduit(@PathVariable Long numeroAssure, @PathVariable Long numeroProduit,
			Model model) {

		ContratDTO contratAffecte = contratProxy.affecterNumeroProduit(numeroAssure, numeroProduit);
		model.addAttribute("contrat", contratAffecte);
		return "finalisationContrat";
	}

	@GetMapping(path = "/finaliserContrat/{numeroAssure}")
	public String finaliserContrat(@PathVariable Long numeroAssure, ContratDTO contratAffecte, Model model) {

		ContratDTO contratFinalise = contratProxy.finaliserContrat(numeroAssure, contratAffecte.getDateDebut());
		Long numeroProduit = contratFinalise.getNumeroProduit();

		model.addAttribute("contratFinalise", contratFinalise);

		List<AssureDTO> assures;
		assures = assureProxy.getAssureNumeroAssure(numeroAssure);
		AssureDTO assureDTO = assures.get(0);
		model.addAttribute("assure", assureDTO);
		
		//List<ProduitDTO> produits;
		//produits = produitProxy.rechercherProduitNumeroProduit(numeroProduit);
		//ProduitDTO produit = produits.get(0);
		//model.addAttribute("produit", produit);

		return "confirmationCreationContrat";
	}

}
