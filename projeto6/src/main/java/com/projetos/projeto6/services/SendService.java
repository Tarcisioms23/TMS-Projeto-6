package com.projetos.projeto6.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.projetos.projeto6.enums.StatusEmail;
import com.projetos.projeto6.models.SendModel;
import com.projetos.projeto6.repositories.SendRepository;

@Service
public class SendService {

	@Autowired
	SendRepository sendRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
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
			}
		 finally {
			return sendRepository.save(sendModel);
		}
	}

	
}
