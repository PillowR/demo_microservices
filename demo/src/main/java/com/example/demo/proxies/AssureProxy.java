package com.example.demo.proxies;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.dto.AssureDTO;

@FeignClient(name = "microservice-assure", url = "localhost:9999")
public interface AssureProxy {

	@GetMapping(path = "previt/listerLesAssures")
	List<AssureDTO> getAllAssure();

	@GetMapping(path = "previt/rechercherAssureNomPrenom/{nom}/{prenom}")
	public List<AssureDTO> getAssureNomPrenom(@PathVariable String nom, @PathVariable String prenom);

	@GetMapping(path = "previt/Assure/numeroAssure/{numeroAssure}")
	public List<AssureDTO> getAssureNumeroAssure(@PathVariable Long numeroAssure);

}
