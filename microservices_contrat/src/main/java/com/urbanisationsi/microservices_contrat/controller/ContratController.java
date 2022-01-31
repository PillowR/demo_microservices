package com.urbanisationsi.microservices_contrat.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisationsi.microservices_contrat.dao.ContratRepository;
import com.urbanisationsi.microservices_contrat.exceptions.ContratIntrouvableException;
import com.urbanisationsi.microservices_contrat.exceptions.CreationContratImpossibleException;
import com.urbanisationsi.microservices_contrat.model.Contrat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "API pour les opérations de CRUD pour les contrats")
@RestController
@RequestMapping(path = "/contrat")
public class ContratController {

	@Autowired
	private ContratRepository contratRepository;

	@ApiOperation(value = "Ajouter un contrat")
	@PostMapping(path = "/ajouterContrat")
	public ResponseEntity<Void> creerContrat(@RequestBody @Valid Contrat contrat) {
		Contrat contratAjoute = contratRepository.save(contrat);

		if (contratAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contratAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Recherche un contrat grâce à son numéro produit à condition que celui-ci existe.")
	@GetMapping(path = "/Contrat/numeroProduit/{numeroProduit}")
	public List<Contrat> rechercherContratParNumeroProduit(@PathVariable Long numeroProduit) {

		List<Contrat> produits = contratRepository.findByNumeroProduit(numeroProduit);

		if (produits.isEmpty())
			throw new ContratIntrouvableException("Le produit avec le numero " + numeroProduit + " n'existe pas !");

		return produits;
	}

	@ApiOperation(value = "Recherche un contrat grâce à son numéro de contrat à condition que celui-ci existe.")
	@GetMapping(path = "/Contrat/numeroContrat/{numeroContrat}")
	public List<Contrat> rechercherContratParNumeroContrat(@PathVariable Long numeroContrat) {

		List<Contrat> produits = contratRepository.findByNumeroContrat(numeroContrat);

		if (produits.isEmpty())
			throw new ContratIntrouvableException("Le produit avec le numero " + numeroContrat + " n'existe pas !");

		return produits;
	}

	@ApiOperation(value = "Recherche un contrat grâce à son numéro d'assure à condition que celui-ci existe.")
	@GetMapping(path = "/Contrat/numeroAssure/{numeroAssure}")
	public List<Contrat> rechercherContratParNumeroAssure(@PathVariable Long numeroAssure) {

		List<Contrat> produits = contratRepository.findByNumeroAssure(numeroAssure);

		if (produits.isEmpty())
			throw new ContratIntrouvableException("Le produit avec le numero " + numeroAssure + " n'existe pas !");

		return produits;
	}

	@GetMapping(path = "/affecterNumeroProduit/{numeroAssure}/{numeroProduit}")
	public ResponseEntity<Contrat> affecterNumeroProduit(@PathVariable Long numeroAssure,
			@PathVariable Long numeroProduit) {

		int min = 100000;
		int max = 999999;
		int numContrat = (int) (Math.random() * (max - min + 1) + min);
		Long numeroContrat = (long) numContrat;

		Contrat contrat = new Contrat();
		contrat.setNumeroProduit(numeroProduit);
		contrat.setNumeroAssure(numeroAssure);
		contrat.setNumeroContrat(numeroContrat);

		contrat = contratRepository.save(contrat);

		return new ResponseEntity<Contrat>(contrat, HttpStatus.CREATED);
	}

	@GetMapping(path = "/finaliserContrat/{numeroAssure}/{dateDebut}")
	public ResponseEntity<Contrat> finaliserContrat(@PathVariable Long numeroAssure, @PathVariable String dateDebut) {

		List<Contrat> contrats = contratRepository.findByNumeroAssure(numeroAssure);

		// On suppose qu'un assure n'a qu'un seul contrat.
		Contrat contrat = contrats.get(0);

		LocalDate dateContrat = LocalDate.parse(dateDebut);
		contrat.setDateDebut(dateContrat);
		contrat = contratRepository.save(contrat);

		return new ResponseEntity<Contrat>(contrat, HttpStatus.CREATED);
	}

}
