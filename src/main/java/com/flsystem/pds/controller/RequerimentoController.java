package com.flsystem.pds.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flsystem.pds.model.StatusRequerimento;
import com.flsystem.pds.model.ModalidadeRequerida;
import com.flsystem.pds.model.Requerimento;
import com.flsystem.pds.repository.filter.RequerimentoFilter;
import com.flsystem.pds.service.CadastroRequerimentoService;

@Controller
@RequestMapping("/requerimentos")
public class RequerimentoController {
	
	private static final String CADASTRO_VIEW = "CadastroRequerimento";
	
	@Autowired
	private CadastroRequerimentoService cadastroRequerimentoService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Requerimento());
		return mv;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("Inicio");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Requerimento requerimento, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			cadastroRequerimentoService.salvar(requerimento);
			attributes.addFlashAttribute("mensagem", "Requerimento salvo com sucesso!");
			if(requerimento.getStatus() != null) {
				return "redirect:/requerimentos/";
			} else {
				return "redirect:/requerimentos/participantes/";
			}
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataCadastro", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") RequerimentoFilter filtro) {
		List<Requerimento> todosRequerimentos = cadastroRequerimentoService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaRequerimentos");
		mv.addObject("requerimentos", todosRequerimentos);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroRequerimentoService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Requerimento excluído com sucesso!");
		return "redirect:/requerimentos";
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Requerimento requerimento) {
		ModelAndView mv = new ModelAndView("Status");
		mv.addObject(requerimento);
		return mv;
	}
	
	@ModelAttribute("todosStatusRequerimento")
	public List<StatusRequerimento> todosStatusRequerimento() {
		return Arrays.asList(StatusRequerimento.values());
	}
	
	@ModelAttribute("todasModalidadesRequeridas")
	public List<ModalidadeRequerida> todasModalidadesRequeridas() {
		return Arrays.asList(ModalidadeRequerida.values());
	}
	
	
}