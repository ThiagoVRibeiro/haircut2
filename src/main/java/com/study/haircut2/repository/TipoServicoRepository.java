package com.study.haircut2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.haircut2.model.TipoServico;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long>{

	List<TipoServico> findByNomeContainingIgnoreCase(String nome); //findBy e ContainingIgnoreCase são predicados que o spring utiliza em cima do nome
}
