package com.flsystem.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flsystem.pds.model.Horario;
import com.flsystem.pds.repository.Horarios;

@Service
public class CadastroHorarioService {

	@Autowired
	private Horarios horarios;

	public void salvar(Horario horario) throws IllegalArgumentException {
		List<Horario> todosHorarios = horarios.findAll();
		boolean temIgual = false;
		for (Horario h : todosHorarios) {
			if (h.getDiaDaSemana() == horario.getDiaDaSemana()) {
				if (h.getHora() == horario.getHora()) {
					temIgual = true;
				}
			}
		}
		if (temIgual == false) {
			horarios.save(horario);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public List<Horario> filtrar() {
		return horarios.findAll();
	}

}
