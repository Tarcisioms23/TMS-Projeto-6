package com.projetos.projeto6.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.projeto6.models.AmigoModel;
import com.projetos.projeto6.models.SendModel;
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

	List<Integer> listanumeroSorteado = new ArrayList<Integer>(Arrays.asList());
	List<Integer> listanumeroEmail = new ArrayList<Integer>(Arrays.asList());

	public boolean ValidaNumero(int numeroSorte, int id_email) {

		boolean validaNumeros = false;

		if (listanumeroSorteado.size() > 0 && !listanumeroSorteado.contains(numeroSorte)
				&& !listanumeroEmail.contains(id_email) && id_email != numeroSorte) {
			validaNumeros = true;
			for (int i = 0; i < listanumeroSorteado.size(); i++) {
				if (listanumeroSorteado.get(i) == id_email 
					&& listanumeroEmail.get(i) == numeroSorte) {
					
					validaNumeros = false;
					break;
				}
			}if(validaNumeros == true) {
				listanumeroSorteado.add(numeroSorte);
				listanumeroEmail.add(id_email);
			}

			
		} else {
			validaNumeros = false;
		}

		if (listanumeroSorteado.size() == 0) {
			if (id_email == numeroSorte) {
				validaNumeros = false;
			} else {
				listanumeroSorteado.add(numeroSorte);
				listanumeroEmail.add(id_email);
				validaNumeros = true;
				System.out.println(listanumeroSorteado.get(0));
			}

		}
		return validaNumeros;
	}

	Random gerador = new Random();

	public void sorteiaAmigo(SendModel sendModel) {

		int qtdEmail = (int) amigoRepository.count();
		for (int i = 1; i <= qtdEmail; i++) {

			AmigoModel amigo = new AmigoModel();
			amigo = amigoRepository.findById(i);

			sendModel.setEmailTo(amigo.getEmail());
			sendModel.setOwner(amigo.getNome());
			AmigoModel amigoSorteado = new AmigoModel();

			int numeroAleatorio = gerador.nextInt(qtdEmail) + 1;

			while (ValidaNumero(numeroAleatorio, i) == false) {
				numeroAleatorio = gerador.nextInt(qtdEmail) + 1;

			}

			System.out.println("i " + i + "numeroAleatorio " + numeroAleatorio);

			amigoSorteado = amigoRepository.findById(numeroAleatorio);

			sendModel.setTexto(amigo.getNome() + " você saiu com: " + amigoSorteado.getNome());

			sendService.sendEmail(sendModel);
			
		}
		listanumeroSorteado.clear();
		listanumeroEmail.clear();
		
	}

}
