package com.study.haircut2.service;

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
}
