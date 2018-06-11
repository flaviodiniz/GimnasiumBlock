package com.flsystem.pds.model;

public enum ModalidadeRequerida {

	FUTSAL("Futsal"), VOLEI("Volei"), HANDEBOL("Handebol"), BASQUETE("Basquete"), TENIS("Tenis");

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	ModalidadeRequerida(String descricao) {
		this.descricao = descricao;
	}

}
