package com.flsystem.pds.model;

public enum Horas {
	
	OITO("08:00"), NOVE("09:00"), DEZ("10:00"), ONZE("11:00"), DOZE("12:00"), TREZE("13:00"), QUATORZE("14:00"), QUINZE("15:00"),
	DEZESSEIS("16:00"), DEZESSETE("17:00"), DEZOITO("18:00"), DEZENOVE("19:00"), VINTE("20:00"), VINTEUM("21:00");

	private String descricao;

	Horas (String descricao) {
			this.descricao = descricao;
		}

	public String getDescricao() {
		return descricao;
	}
	
}
