package com.urbanisationsi.microservices_produit.http.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisationsi.microservices_produit.Exception.ProduitIntrouvableException;
import com.urbanisationsi.microservices_produit.Model.Produit;
import com.urbanisationsi.microservices_produit.dao.ProduitRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "API pour les opérations de CRUD pour les produits")  
@RestController
@RequestMapping(path = "/prod")
public class ProduitController {

	@Autowired
	private ProduitRepository produitRepository;
	
	@GetMapping(path = "/listerLesProduits")
	public @ResponseBody Iterable<Produit> getAllProduits() {
		return produitRepository.findAll();
	}

	@ApiOperation(value = "Recherche un produit grâce à son numéro produit à condition que celui-ci existe.")  
	@GetMapping(path = "/Produit/numeroProduit/{numeroProduit}")
	public List<Produit> rechercherProduitNumeroProduit(@PathVariable Long numeroProduit) {

		List<Produit> produits = produitRepository.findByNumeroProduit(numeroProduit);

		if (produits.isEmpty())
			throw new ProduitIntrouvableException("Le produit avec le numero " + numeroProduit + " n'existe pas !");

		return produits;
	}

	@ApiOperation(value = "Recherche un produit grâce à son id à condition que celui-ci existe.")  
	@GetMapping(path = "/Produit/id/{numeroProduit}")
	public Produit rechercherProduitId(@PathVariable Integer id) {

		Optional<Produit> produits = produitRepository.findById(id);

		if (produits.isEmpty())
			throw new ProduitIntrouvableException("Le produit avec l'id " + id + " n'existe pas !");

		return produits.get();
	}
	
	@PostMapping(path = "/ajouterProduit")
	public ResponseEntity<Void> creerProduit(@RequestBody @Valid Produit produit) {
		Produit produitAjoute = produitRepository.save(produit);

		if (produitAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produitAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
