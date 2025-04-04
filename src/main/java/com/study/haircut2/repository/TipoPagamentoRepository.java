package com.study.haircut2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.haircut2.model.TipoPagamento;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long> {
	List<TipoPagamento> findByNomeContainingIgnoreCase(String nome);
}
