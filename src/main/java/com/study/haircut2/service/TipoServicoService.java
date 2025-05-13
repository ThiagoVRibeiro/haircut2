package com.study.haircut2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.haircut2.exception.TipoServicoNotFoundException;
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
	
	//método que busca por id para poder apagar
	public TipoServico buscarTipoServicoPorId(Long id)throws TipoServicoNotFoundException {
		Optional<TipoServico> opt = tipoServicoRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new TipoServicoNotFoundException ("Tipo de Servico com id: " + id + " não existe.");
		}
	}
	
	
	//método responsável de fato para apagar
	public void apagarTipoDeServico(Long id) throws TipoServicoNotFoundException{
		TipoServico tipoServico = buscarTipoServicoPorId(id);
		tipoServicoRepository.delete(tipoServico);
	}
	
	//método que irá editar
	public TipoServico editarTipoServico(TipoServico tipoServico) {
		return tipoServicoRepository.save(tipoServico);
	}
}
