package com.study.haircut2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.haircut2.model.TipoPagamento;
import com.study.haircut2.repository.TipoPagamentoRepository;

@Service
public class TipoPagamentoService {
	
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	
	//Salvar tipo de pagamento
	public TipoPagamento criarTipoPagamento(TipoPagamento tipoPagamento) {
		return tipoPagamentoRepository.save(tipoPagamento);
	}
	
	//ListarPagamento
	public List<TipoPagamento> buscarTodosTiposPagamentos(){
		return tipoPagamentoRepository.findAll();
	}
}
