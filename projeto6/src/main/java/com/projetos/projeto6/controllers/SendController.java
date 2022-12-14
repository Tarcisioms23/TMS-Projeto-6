package com.projetos.projeto6.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.projeto6.dtos.SendDto;
import com.projetos.projeto6.models.SendModel;
import com.projetos.projeto6.services.SendService;

@RestController
public class SendController {

	
	@Autowired 
	SendService sendService;
	
	
	@PostMapping("/EmailAmigo")
	public ResponseEntity<SendModel> envioEmail(@RequestBody @Valid SendDto sendDto ){
		SendModel sendModel = new SendModel();
		BeanUtils.copyProperties(sendDto, sendModel);
		sendService.sorteiaAmigo(sendModel);
		return new ResponseEntity<>(sendModel, HttpStatus.CREATED);
	}
}
