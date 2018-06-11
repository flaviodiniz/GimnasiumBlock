package com.flsystem.pds.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flsystem.pds.model.Participante;
import com.flsystem.pds.model.Requerimento;
import com.flsystem.pds.repository.Requerimentos;
//import com.flsystem.pds.model.Requerimento;
import com.flsystem.pds.repository.filter.ParticipanteFilter;
import com.flsystem.pds.service.CadastroParticipanteService;

@Controller
@RequestMapping("/requerimentos/participantes")
public class ParticipanteController {

	private static final String CADASTRO_VIEW = "CadastroParticipantes";
		
	@Autowired
	private Requerimentos requerimentos;
	private Requerimento requerimento;
	
	@Autowired
	private CadastroParticipanteService cadastroParticipanteService;
	
	@RequestMapping()
	public ModelAndView participantes() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Participante participante, Errors errors) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		try {
			participante.setRequerimento(requerimento);
			cadastroParticipanteService.salvar(participante);
			return "redirect:/requerimentos/participantes";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataCadastro", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(@ModelAttribute("filtro") ParticipanteFilter filtro) {
		List<Participante> todosParticipantes = cadastroParticipanteService.filtrar(filtro);
		List<Requerimento> todosRequerimentos = requerimentos.findAll();
		ArrayList<Participante> participanteDoRequerimento = new ArrayList<Participante>();
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if (todosRequerimentos.size() > 0) {
			requerimento = todosRequerimentos.get(todosRequerimentos.size()-1);
		}
		for(Participante p: todosParticipantes) {
			if(p.getRequerimento().equals(requerimento)) {
				participanteDoRequerimento.add(p);
			}
		}
		List<Participante> participantes = participanteDoRequerimento;
		mv.addObject("requerimentos", requerimento);
		mv.addObject("participantes", participantes);
		return mv;
	}

}
