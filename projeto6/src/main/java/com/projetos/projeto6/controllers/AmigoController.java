package com.projetos.projeto6.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.projeto6.models.AmigoModel;
import com.projetos.projeto6.services.AmigoService;

@RestController
@RequestMapping("api/projeto_6")
public class AmigoController {

	
	
	@Autowired
	AmigoService amigoService;
	
	
	@PostMapping("/amigo")
	public AmigoModel salvaAmigo(@RequestBody AmigoModel amigoModel) {
		amigoService.salvaAlteraAmigo(amigoModel);
		return amigoModel;
	}
	
	@GetMapping("/amigos")
	public List<AmigoModel> listaAmigos(){
		return amigoService.listaAmigos();
	}
	
	@GetMapping("/amigo/{id}")
	public AmigoModel buscaAmigo(@PathVariable(value="id") long id) {
		return amigoService.buscaPorId(id);
	}
	
	
	@PutMapping("/amigo")
	public AmigoModel alteraAmigo(@RequestBody AmigoModel amigoModel) {
		return amigoService.salvaAlteraAmigo(amigoModel);
		
	}
	
	@DeleteMapping("/amigo/{id}")
	public String deltaAmiggo(@PathVariable(value ="id") Long id) {
		return amigoService.deletaAmigo(id);
	}
}
