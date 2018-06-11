package com.flsystem.pds.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Enumerated(EnumType.STRING)
	private Horas hora;

	@Enumerated(EnumType.STRING)
	private DiasDaSemana diaDaSemana;

	@OneToOne()
	@JoinColumn(name = "REQUERIMENTO_FK")
	private Requerimento requerimento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Horas getHora() {
		return hora;
	}

	public void setHora(Horas hora) {
		this.hora = hora;
	}

	public DiasDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiasDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public Requerimento getRequerimento() {
		return requerimento;
	}

	public void setRequerimento(Requerimento requerimento) {
		this.requerimento = requerimento;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
//		result = prime * result + ((diaDaSemana == null) ? 0 : diaDaSemana.hashCode());
//		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
//		result = prime * result + ((requerimento == null) ? 0 : requerimento.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horario other = (Horario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (diaDaSemana != other.diaDaSemana)
			return false;
		if (hora != other.hora)
			return false;
		if (requerimento == null) {
			if (other.requerimento != null)
				return false;
		} else if (!requerimento.equals(other.requerimento))
			return false;
		return true;
	}

}
