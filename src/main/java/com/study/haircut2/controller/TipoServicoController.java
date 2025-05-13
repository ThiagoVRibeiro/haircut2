package com.study.haircut2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.haircut2.exception.TipoServicoNotFoundException;
import com.study.haircut2.model.TipoServico;
import com.study.haircut2.service.TipoServicoService;

import jakarta.validation.Valid;

@Controller
public class TipoServicoController {
	
	@Autowired
	private TipoServicoService tipoServicoService;
	
	@GetMapping("/tipo-servico")
	public String listarTipoServico(Model model) {
		List<TipoServico> tipoServico = tipoServicoService.buscarTodosTiposServicos();
		model.addAttribute("listaTipoServico", tipoServico);
		return "/tiposervico/lista-tipo-servico";
	}
	
	@GetMapping("/novo-tipo-servico")
	public String novoTipoServico(Model model) {
		TipoServico tipoServico = new TipoServico();
		model.addAttribute("novoTipoServico", tipoServico);
		return "/tiposervico/novo-tipo-servico";
	}
	
	//método que irá cadastrar no banco
	@PostMapping("/cadastrarTipoServico")
	public String cadastrarTipoServico(@ModelAttribute("novoTipoServico") @Valid TipoServico tipoServico,
			BindingResult erros,
			RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "/tiposervico/novo-tipo-servico";
		}
		tipoServicoService.criarTipoServico(tipoServico);
		attributes.addFlashAttribute("mensagem", "Tipo de servico salvo com sucesso!");
		return "redirect:/tipo-servico";
	}
	
	//método que irá apagar
	@GetMapping("/apagar-tipo-servico/{id}")
	public String apagarTipoServico(@PathVariable("id") long id, RedirectAttributes attributes) {
		try {
			tipoServicoService.apagarTipoDeServico(id);
		} catch (TipoServicoNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/tipo-servico";
	}
	
	//método que irá editar
	@GetMapping("/editar-tipo-servico/{id}")
	public String editarTipoServicoForm(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
		try {
			TipoServico tipoServico = tipoServicoService.buscarTipoServicoPorId(id);
			model.addAttribute("objetoTipoServico", tipoServico);
			return "/tiposervico/editar-tipo-servico";
		} catch (TipoServicoNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/tipo-servico";
	}
	
	//método que irá gravar a edição
	@PostMapping("/editar-tipo-servico/{id}")
	public String editarTipoServico(@PathVariable("id") long id, @ModelAttribute("objetoTipoServico") @Valid TipoServico tipoServico,
			BindingResult erros) {
		if(erros.hasErrors()) {
			tipoServico.setId(id);
			return "/tiposervico/editar-tipo-servico";
		}
		tipoServicoService.editarTipoServico(tipoServico);
		return "redirect:/tipo-servico";
	}
	
}
