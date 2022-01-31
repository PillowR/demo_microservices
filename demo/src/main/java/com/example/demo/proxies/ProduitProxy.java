package com.example.demo.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ProduitDTO;

@FeignClient(name = "microservice-produit", url = "localhost:9998")
public interface ProduitProxy {

	@GetMapping(path = "prod/listerLesProduits")
	public List<ProduitDTO> getAllProduits();

	@GetMapping(path = "prod/Produit/numeroProduit/{numeroProduit}")
	public List<ProduitDTO> rechercherProduitNumeroProduit(@PathVariable Long numeroProduit);

}