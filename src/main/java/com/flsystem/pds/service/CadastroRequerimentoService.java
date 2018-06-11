package com.flsystem.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.flsystem.pds.model.StatusRequerimento;
import com.flsystem.pds.model.Requerimento;
import com.flsystem.pds.repository.Requerimentos;
import com.flsystem.pds.repository.filter.RequerimentoFilter;

@Service
public class CadastroRequerimentoService {

	@Autowired
	private Requerimentos requerimentos;

	public void salvar(Requerimento requerimento) {
		try {
			requerimentos.save(requerimento);

		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato da data inválido!");
		}
	}

	public void excluir(Long codigo) {
		requerimentos.delete(codigo);
	}

	public String receber(Long codigo) {
		Requerimento requerimento = requerimentos.findOne(codigo);
		requerimento.setStatus(StatusRequerimento.DEFERIDO);
		requerimentos.save(requerimento);
		return StatusRequerimento.INDEFERIDO.getDescricao();
	}

	public List<Requerimento> filtrar(RequerimentoFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return requerimentos.findByDescricaoContaining(descricao);
	}

}
