package com.study.haircut2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.haircut2.exception.ClienteNotFoundException;
import com.study.haircut2.exception.TipoPagamentoNotFoundException;
import com.study.haircut2.model.Cliente;
import com.study.haircut2.model.TipoPagamento;
import com.study.haircut2.service.ClienteService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/cliente")
	public String listarClientes(Model model) {
		List<Cliente> clientes = clienteService.buscarTodosClientes();
		model.addAttribute("listaClientes", clientes);
		return "/cliente/listar-cliente";
	}
	
	@GetMapping("/novo-cliente")
	public String novoCliente(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("novoCliente", cliente);
		return "/cliente/novo-cliente";
	}
	//Novo Cliente
	@PostMapping("/cadastrarCliente")
	public String cadastrarCliente(@ModelAttribute("novoCliente") @Valid Cliente cliente,
			BindingResult erros,
			RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "/cliente/novo-cliente";
		}
		clienteService.criarCliente(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return "redirect:/cliente";
	}
	
	
	@PostMapping("/buscar-cliente")
	public String buscarCliente(Model model, @Param("nome") String nome) {
		if(nome == null) {
			return "redirect:/cliente";
		}
		List<Cliente> clientes = clienteService.buscarTodosClientesPorNome(nome);
		model.addAttribute("listaClientes", clientes);
		return "/cliente/listar-cliente";
	}
	
	
	//Editar cliente
	//método que irá preencher os dados para editar
	@GetMapping("editar-cliente/{id}")
	public String editarFormCliente(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
		try {
			Cliente cliente = clienteService.buscarClientePorId(id);
			model.addAttribute("objetoCliente", cliente);
			return "/cliente/editar-cliente";
		}catch(ClienteNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}
		
	//método que irá salvar o que foi editado
	
	@PostMapping("/editar-cliente/{id}")
	public String editarCliente(@PathVariable("id") long id,
									@ModelAttribute("objetoCliente") @Valid Cliente cliente,
									BindingResult erros) {
		if(erros.hasErrors()) {
			cliente.setId(id);
			return "/cliente/editar-cliente";
		}
		clienteService.editarCliente(cliente);
		return "redirect:/cliente";
	}
	
	//Apagar Cliente
	
	@GetMapping("/apagar-cliente/{id}")
	public String apagarCliente(@PathVariable("id") long id, RedirectAttributes attributes) {
		try {
			clienteService.apagarCliente(id);
		}catch(ClienteNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/cliente";
	}
	
}
