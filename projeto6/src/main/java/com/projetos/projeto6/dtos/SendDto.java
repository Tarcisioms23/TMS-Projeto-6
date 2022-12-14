package com.projetos.projeto6.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SendDto {

	@NotBlank
	private String subject;
	
	@NotBlank
	private String texto;
	
	@NotBlank
	@Email
	private String emailFrom;
}
