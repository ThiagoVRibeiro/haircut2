package com.study.haircut2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TipoPagamentoController {

	@GetMapping("/")
	public String acessarPaginaInicial() {
		return "index";
	}
	
	@GetMapping("/tipo-pagamento")
	public String listarTipoPagamento() {
		return "lista-tipo-pagamento";
	}
}
