package com.flsystem.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flsystem.pds.model.Participante;
import com.flsystem.pds.repository.Participantes;
import com.flsystem.pds.repository.filter.ParticipanteFilter;

@Service
public class CadastroParticipanteService {
	
	@Autowired
	private Participantes participantes;
	
	public Participantes getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Participantes participantes) {
		this.participantes = participantes;
	}

	public void salvar(Participante participante) {
		participantes.save(participante);
	}
	
	public List<Participante> filtrar(ParticipanteFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return participantes.findByNomeContaining(descricao);
	}

}
