package com.example.demo.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ContratDTO;

@FeignClient(name = "microservice-contrat", url = "localhost:9997", decode404 = true)
public interface ContratProxy {

	/*
	 * @GetMapping(path = "contrat/Contrat/numeroAssure/{numeroAssure}") public
	 * List<ContratDTO> rechercherContratNumeroAssure(@PathVariable Long
	 * numeroAssure);
	 * 
	 * @PostMapping(path = "/contrat/ajouterContrat") public ContratDTO
	 * creerContrat(@RequestBody ContratDTO contrat);
	 */
	@GetMapping(path="/contrat/finaliserContrat/{numeroAssure}/{dateDebut}")
	public ContratDTO finaliserContrat(@PathVariable Long numeroAssure, @PathVariable String dateDebut);

	@GetMapping(path = "/contrat/affecterNumeroProduit/{numeroAssure}/{numeroProduit}")
	public ContratDTO affecterNumeroProduit( @PathVariable Long numeroAssure, @PathVariable Long numeroProduit);

}
