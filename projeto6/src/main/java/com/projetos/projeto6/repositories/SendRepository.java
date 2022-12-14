package com.projetos.projeto6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.projeto6.models.SendModel;

public interface SendRepository extends JpaRepository<SendModel, Long> {

	
}
