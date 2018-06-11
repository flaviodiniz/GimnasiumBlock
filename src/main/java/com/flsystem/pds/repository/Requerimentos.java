package com.flsystem.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flsystem.pds.model.Requerimento;

public interface Requerimentos extends JpaRepository<Requerimento, Long> {

	public List<Requerimento> findByDescricaoContaining(String descricao);
}

