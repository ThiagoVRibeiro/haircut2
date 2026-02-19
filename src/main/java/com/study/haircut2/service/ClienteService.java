package com.study.haircut2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.haircut2.exception.ClienteNotFoundException;
import com.study.haircut2.exception.TipoPagamentoNotFoundException;
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
	
	//editarcliente
	public Cliente editarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	//usado para editar
	public Cliente buscarClientePorId(Long id) throws ClienteNotFoundException{
		Optional<Cliente> opt = clienteRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new ClienteNotFoundException("Cliente com id: " + id + " não existe.");
		}
	}
	//apagar
	public void apagarCliente(Long id) throws ClienteNotFoundException{
		Cliente cliente = buscarClientePorId(id);
		clienteRepository.delete(cliente);
	}
	
	
	
	
}
