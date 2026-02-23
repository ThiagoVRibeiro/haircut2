package com.study.haircut2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.haircut2.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

	//Adicionado para gerar relatório de serviços realizados
	List<Servico> findAllByOrderByDataDesc();
}
