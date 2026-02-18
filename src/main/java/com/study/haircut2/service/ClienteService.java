package com.study.haircut2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.haircut2.model.Cliente;
import com.study.haircut2.model.TipoPagamento;
import com.study.haircut2.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//Salvar cliente
	public Cliente criarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	//listaTodosCliente
	public List<Cliente> buscarTodosClientes(){
		return clienteRepository.findAll();
	}
	
	//buscarClientePorNome
	public List<Cliente> buscarTodosClientesPorNome(String nome){
		return clienteRepository.findByNomeContainingIgnoreCase(nome);
	}

}
