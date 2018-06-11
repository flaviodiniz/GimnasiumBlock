package com.flsystem.pds.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flsystem.pds.model.DiasDaSemana;
import com.flsystem.pds.model.Horario;
import com.flsystem.pds.model.Horas;
import com.flsystem.pds.model.Requerimento;
import com.flsystem.pds.repository.Requerimentos;
import com.flsystem.pds.repository.filter.HorarioFilter;
import com.flsystem.pds.service.CadastroHorarioService;

@Controller
@RequestMapping("/requerimentos/horarios")
public class HorarioController {

	private static final String CADASTRO_VIEW = "CadastroHorarios";

	@Autowired
	private CadastroHorarioService casdastroHorarioService;

	@Autowired
	private Requerimentos requerimentos;
	private Requerimento requerimento;

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Horario horario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {
			horario.setRequerimento(requerimento);
			casdastroHorarioService.salvar(horario);
			attributes.addFlashAttribute("mensagem", "Requerimento completado com sucesso!");
			return "redirect:/requerimentos/inicio";
		} catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("mensagem", "Horario já Existente!");
			errors.reject(null, "Horario já Existente!");
			return CADASTRO_VIEW;
		}
		
	}

	@RequestMapping()
	public ModelAndView pesquisar(@ModelAttribute("filtro") HorarioFilter filtro) {
		List<Horario> todosHorarios = casdastroHorarioService.filtrar();
		List<Requerimento> todosRequerimentos = requerimentos.findAll();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Horario());
		
		if (todosRequerimentos.size() > 0) {
			requerimento = todosRequerimentos.get(todosRequerimentos.size() - 1);
		}
		mv.addObject("horarios", todosHorarios);
		return mv;
	}

	@ModelAttribute("diasDaSemana")
	public List<DiasDaSemana> diasDaSemana() {
		return Arrays.asList(DiasDaSemana.values());
	}
	
	@ModelAttribute("horas")
	public List<Horas> horas() {
		return Arrays.asList(Horas.values());
	}
}
