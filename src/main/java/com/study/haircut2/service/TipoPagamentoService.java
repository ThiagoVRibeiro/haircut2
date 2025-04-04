package com.study.haircut2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.haircut2.exception.TipoPagamentoNotFoundException;
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
	
	public TipoPagamento buscarTipoPagamentoPorId(Long id)throws TipoPagamentoNotFoundException {
		Optional<TipoPagamento> opt = tipoPagamentoRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new TipoPagamentoNotFoundException ("Tipo de pagamento com id: " + id + " não existe.");
		}
	}
	
	public void apagarTipoDePagamento(Long id) throws TipoPagamentoNotFoundException{
		TipoPagamento tipoPagamento = buscarTipoPagamentoPorId(id);
		tipoPagamentoRepository.delete(tipoPagamento);
	}
	
	public TipoPagamento editarTipoPagamento(TipoPagamento tipoPagamento) {
		return tipoPagamentoRepository.save(tipoPagamento);
	}
	
	public List<TipoPagamento> buscarTodosTiposPagamentosPorNome(String nome){
		return tipoPagamentoRepository.findByNomeContainingIgnoreCase(nome);
	}
}
