package com.projetos.projeto6.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projetos.projeto6.enums.StatusEmail;

import lombok.Data;



@Data
@Entity
@Table(name = "TB_EMAIL")
public class SendModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long IdSend;
	private String owner;
	private String emailFrom;
	private String emailTo;
	private String subject;
	
	@Column(columnDefinition = "TEXT") //Aumentar a quantidade caracter e nao limitar 250
	private String texto;
	
	private LocalDateTime senDataEmail;
	
	private StatusEmail statusemail; // Enum para saber se foi enviado
	

}
