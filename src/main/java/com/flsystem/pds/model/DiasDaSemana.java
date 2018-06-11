package com.flsystem.pds.model;

public enum DiasDaSemana {

	SEGUNDA("Segunda"), TERCA("Terca"), QUARTA("Quarta"), QUINTA("Quinta"), SEXTA("Sexta");

	private String descricao;

	DiasDaSemana (String descricao) {
			this.descricao = descricao;
		}

	public String getDescricao() {
		return descricao;
	}

}
