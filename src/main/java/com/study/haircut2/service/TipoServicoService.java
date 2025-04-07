package com.study.haircut2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.haircut2.model.TipoPagamento;
import com.study.haircut2.model.TipoServico;
import com.study.haircut2.repository.TipoServicoRepository;

@Service
public class TipoServicoService {
	
	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	
	public TipoServico criarTipoServico(TipoServico tipoServico) {
		return tipoServicoRepository.save(tipoServico);
	}
	
	//Método para listar
	public List<TipoServico> buscarTodosTiposServicos(){
		return tipoServicoRepository.findAll();
	}
}
