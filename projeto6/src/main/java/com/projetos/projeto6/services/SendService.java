package com.projetos.projeto6.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.projetos.projeto6.enums.StatusEmail;
import com.projetos.projeto6.models.AmigoModel;
import com.projetos.projeto6.models.SendModel;
import com.projetos.projeto6.repositories.AmigoRepository;
import com.projetos.projeto6.repositories.SendRepository;

@Service
public class SendService {

	@Autowired
	SendRepository sendRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	AmigoRepository amigoRepository;

	// Metodo que envia email

	@SuppressWarnings("finally")
	public SendModel sendEmail(SendModel sendModel) {
		sendModel.setSenDataEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(sendModel.getEmailTo());
			String emailEnviar = sendModel.getEmailTo();
			message.setTo(emailEnviar);
			message.setSubject(sendModel.getSubject());
			message.setText(sendModel.getTexto());
			mailSender.send(message);
			sendModel.setStatusemail(StatusEmail.SENT);
		} catch (Exception e) {
			sendModel.setStatusemail(StatusEmail.ERROR);
		} finally {
			return sendRepository.save(sendModel);
		}
	}

	// Metodo que valida o numero para nao repetir para a mesma pessoa e nao sair ele mesmo

	List<Integer> listanumeroSorteado = new ArrayList<Integer>(Arrays.asList());
	List<Integer> listanumeroEmail = new ArrayList<Integer>(Arrays.asList());

	public boolean ValidaNumero(int numeroSorte, int id_email) {

		boolean validaNumeros = false;

		if (listanumeroSorteado.size() > 0 && !listanumeroSorteado.contains(numeroSorte)
				&& !listanumeroEmail.contains(id_email) && id_email != numeroSorte) {
			validaNumeros = true;
			for (int i = 0; i < listanumeroSorteado.size(); i++) {
				if (listanumeroSorteado.get(i) == id_email && listanumeroEmail.get(i) == numeroSorte) {

					validaNumeros = false;
					break;
				}
			}
			if (validaNumeros == true) {
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

	// Metodos que sorteia o Amigo

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

			sendEmail(sendModel);

		}
		listanumeroSorteado.clear();
		listanumeroEmail.clear();

	}
}
