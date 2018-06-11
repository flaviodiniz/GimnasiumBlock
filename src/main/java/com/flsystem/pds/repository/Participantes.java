package com.flsystem.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flsystem.pds.model.Participante;

public interface Participantes extends JpaRepository<Participante, Long> {
	
	public List<Participante> findByNomeContaining(String nome);

}
