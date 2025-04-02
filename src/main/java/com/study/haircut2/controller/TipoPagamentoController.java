package com.study.haircut2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.haircut2.model.TipoPagamento;
import com.study.haircut2.service.TipoPagamentoService;

import jakarta.validation.Valid;

@Controller
public class TipoPagamentoController {
	
	@Autowired
	private TipoPagamentoService tipoPagamentoService;

	@GetMapping("/")
	public String acessarPaginaInicial() {
		return "index";
	}
	
	@GetMapping("/tipo-pagamento")
	public String listarTipoPagamento(Model model) {
		List<TipoPagamento> tipoPagamentos = tipoPagamentoService.buscarTodosTiposPagamentos();
		model.addAttribute("listaTipoPagamento", tipoPagamentos);
		return "lista-tipo-pagamento";
	}
	
	@GetMapping("/novo-tipo-pagamento")
	public String novoTipoPagamento(Model model) {
		TipoPagamento tipoPagamento = new TipoPagamento();
		model.addAttribute("novoTipoPagamento", tipoPagamento);
		return "/novo-tipo-pagamento";
	}
	
	@PostMapping("/cadastrarTipoPagamento")
	public String cadastrarTipoPagamento(@ModelAttribute("novoTipoPagamento") @Valid TipoPagamento tipoPagamento,
			BindingResult erros,
			RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "/novo-tipo-pagamento";
		}
		tipoPagamentoService.criarTipoPagamento(tipoPagamento);
		attributes.addFlashAttribute("mensagem", "Tipo de pagamento salvo com sucesso!");
		return "redirect:/tipo-pagamento";
	}
}
