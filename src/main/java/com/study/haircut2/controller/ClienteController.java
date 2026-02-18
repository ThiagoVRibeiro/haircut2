package com.study.haircut2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
//	@PostMapping("/buscar")
//	public String buscarTipoPagamento(Model model, @Param("nome") String nome) {
//		if(nome == null) {
//			return "redirect:/tipo-pagamento";
//		}
//		List<TipoPagamento> tipoPagamentos = tipoPagamentoService.buscarTodosTiposPagamentosPorNome(nome);
//		model.addAttribute("listaTipoPagamento", tipoPagamentos);
//		return "/tipopagamento/lista-tipo-pagamento";
//	}
}
