package com.urbanisationsi.microservices_assure.controleur;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.urbanisationsi.microservices_assure.configuration.ApplicationPropertiesConfiguration;
import com.urbanisationsi.microservices_assure.dao.AssureRepository;
import com.urbanisationsi.microservices_assure.exceptions.AssureIntrouvableExcpetion;
import com.urbanisationsi.microservices_assure.modele.Assure;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "API pour les opérations CRUD pour les assurés")
@RestController
@RequestMapping(path = "/previt")
public class AssureControleur {

	@Autowired
	private AssureRepository assureRepository; // injection du dao

	@Autowired
	ApplicationPropertiesConfiguration appProperties;

	@ApiOperation(value = "Recherche un assuré grâce à son ID à condition que celui-ci existe.")

	@PostMapping(path = "/ajouterAssure")
	public ResponseEntity<Void> creerAssure(@RequestBody @Valid Assure assure) {
		Assure assureAjoute = assureRepository.save(assure);

		if (assureAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(assureAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/listerLesAssures")
	public @ResponseBody Iterable<Assure> getAllAssures() {
		return assureRepository.findAll();
	}

	@GetMapping(path = "/listerLesAssure")
	public List<Assure> getAllAssure() {

		Iterable<Assure> assuresIterable = assureRepository.findAll();
		List assuresList = StreamSupport.stream(assuresIterable.spliterator(), false).collect(Collectors.toList());
		List<Assure> listeLimitee = assuresList.subList(0, appProperties.getLimiteNombreAssure());
		return listeLimitee;
	}

	@GetMapping(path = "/Assure/numeroPersonne/{numeroPersonne}")
	public List<Assure> rechercherAssureNumeroPersonne(@PathVariable Long numeroPersonne) {
		return assureRepository.rechercherAssureNumeroPersonne(numeroPersonne);
	}

	@GetMapping(path = "/Assure/numeroAssure/{numeroAssure}")
	public List<Assure> rechercherAssureNumeroAssure(@PathVariable Long numeroAssure) {

		List<Assure> assures = assureRepository.rechercherAssureNumeroAssure(numeroAssure);

		return assures;
	}

	@GetMapping(path = "/rechercherAssureNomPrenom/{nom}/{prenom}")
	public List<Assure> rechercherAssureNumeroPersonne(@PathVariable String nom, @PathVariable String prenom) {
		return assureRepository.findByNomAndPrenom(nom, prenom);
	}

	@GetMapping(path = "/Assure/nomContient/{pon}")
	public List<Assure> rechercherAssureContient(@PathVariable String pon) {
		return assureRepository.findByNomContaining(pon);
	}

	@GetMapping(path = "/Assure/avantDateNaissance/{date}")
	public List<Assure> rechercherAssureNaissanceAvant(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
		return assureRepository.findByDateNaissanceBefore(date);
	}

	/*
	 * @GetMapping(path = "Assure/{id}") public @ResponseBody Optional<Assure>
	 * rechercheAssureId(@PathVariable Integer id) { Optional<Assure> ass =
	 * assureRepository.findById(id); if (!ass.isPresent()) throw new
	 * AssureIntrouvableExcpetion("L'assure id = " + id + " n'existe pas."); return
	 * ass; }
	 */

	@ApiOperation(value = "Recherche un assuré grâce à son ID à condition que celui-ci existe.")
	@GetMapping(path = "Assure/{id}")
	public MappingJacksonValue rechercherAssureId(@PathVariable Integer id) {
		Optional<Assure> assure = assureRepository.findById(id);

		if (!assure.isPresent())
			throw new AssureIntrouvableExcpetion("L'assure avec l'id " + id + " n'existe pas !");

		FilterProvider listeFiltres = creerFiltre("filtreDynamiqueJson", "dossierMedical");

		Assure a = assure.get();
		ArrayList<Assure> ar = new ArrayList<Assure>();
		ar.add(assure.get());

		return filtrerAssures(ar, listeFiltres);
	}

	@DeleteMapping(path = "/Assure/{id}")
	public void supprimerAssurer(@PathVariable Integer id) {
		assureRepository.deleteById(id);
	}

	@PutMapping(path = "/modifierAssure")
	public void modifierAssure(@RequestBody Assure assure) {
		assureRepository.save(assure);
	}

	public FilterProvider creerFiltre(String nomDuFiltre, String attribut) {

		SimpleBeanPropertyFilter unFiltre;
		if (attribut != null) {
			unFiltre = SimpleBeanPropertyFilter.serializeAllExcept(attribut);
		} else {
			unFiltre = SimpleBeanPropertyFilter.serializeAll();
		}

		return new SimpleFilterProvider().addFilter(nomDuFiltre, unFiltre);
	}

	public MappingJacksonValue filtrerAssures(List<Assure> assures, FilterProvider listeFiltres) {

		MappingJacksonValue assuresFiltres = new MappingJacksonValue(assures);

		assuresFiltres.setFilters(listeFiltres);

		return assuresFiltres;
	}

}
