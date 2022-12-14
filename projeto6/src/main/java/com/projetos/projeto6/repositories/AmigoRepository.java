package com.projetos.projeto6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.projeto6.models.AmigoModel;

public interface AmigoRepository extends JpaRepository<AmigoModel, Long>{

		AmigoModel findById (long amigoID);
		
}
