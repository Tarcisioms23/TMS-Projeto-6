package com.projetos.projeto6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.projeto6.models.AmigoModel;
import com.projetos.projeto6.repositories.AmigoRepository;
import com.projetos.projeto6.repositories.SendRepository;

@Service
public class AmigoService {

	@Autowired
	AmigoRepository amigoRepository;

	@Autowired
	SendRepository sendRepository;

	@Autowired
	SendService sendService;

	public void sendAmigo(AmigoModel amigoModel) {

	}

	public AmigoModel salvaAlteraAmigo(AmigoModel amigoModel) {
		return amigoRepository.save(amigoModel);
	}

	public List<AmigoModel> listaAmigos() {
		return amigoRepository.findAll();
	}

	public AmigoModel buscaPorId(long id) {
		return amigoRepository.findById(id);
	}

	public String deletaAmigo(Long id) {
		amigoRepository.deleteById(id);

		return "Usuario Deletado" + id;
	}



}
