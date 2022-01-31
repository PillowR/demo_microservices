package com.urbanisationsi.microservices_assure.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.urbanisationsi.microservices_assure.modele.Assure;

public interface AssureRepository extends CrudRepository<Assure, Integer> {

	/*
	 * void delete(Assure entity);
	 * 
	 * void deleteById(Integer id);
	 * 
	 * boolean existsById(Integer id);
	 * 
	 * Iterable<Assure> findAll();
	 * 
	 * Optional<Assure> findById(Integer id);
	 * 
	 * <S extends Personne> S save(S entity);
	 */

	List<Assure> findByNumeroPersonne(Long numeroPersonne);

	List<Assure> findByNomAndPrenom(String nom, String prenom);

	List<Assure> findByNomContaining(String pon);

	List<Assure> findByDateNaissanceBefore(LocalDate date);
	
	@Query("from Assure a where a.numeroAssure = :na")
	List<Assure>  rechercherAssureNumeroAssure(@Param("na") Long numeroAssure);
	
	@Query("from Assure a where a.numeroPersonne = :np")
	List<Assure>  rechercherAssureNumeroPersonne(@Param("np") Long numeroPersonne);

	Optional<Assure> findByNumeroAssure(Long numeroAssure);

}
