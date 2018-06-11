package com.flsystem.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flsystem.pds.model.Horario;
import com.flsystem.pds.model.Horas;

public interface Horarios extends JpaRepository<Horario, Long> {
	
	public List<Horario> findByHoraContaining(Horas hora);

}
