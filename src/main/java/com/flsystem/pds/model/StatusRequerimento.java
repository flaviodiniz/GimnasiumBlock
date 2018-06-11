package com.flsystem.pds.model;

public enum StatusRequerimento {

	INDEFERIDO("Indeferido"), DEFERIDO("Deferido");

	private String descricao;

	StatusRequerimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
