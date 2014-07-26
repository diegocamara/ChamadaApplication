package com.application.chamada.domain;

public enum DiaSemanaEnum {

	SEGUNDA(1, "Segunda"), TERCA(2, "Terça"), QUARTA(3, "Quarta"), QUINTA(4,
			"Quinta"), SEXTA(5, "Sexta"), SABADO(6, "Sabado"), DOMINGO(7,
			"Domingo");

	private Integer codigo;
	private String descricao;

	private DiaSemanaEnum(Integer codigo, String descricao) {
		setCodigo(codigo);
		setDescricao(descricao);
	}

	public static DiaSemanaEnum obterDiaSemanaPorIndex(int index) {

		DiaSemanaEnum diaSemanaEnum = null;

		switch (index) {
		case 0: {
			diaSemanaEnum = DiaSemanaEnum.SEGUNDA;
			break;
		}
		case 1: {
			diaSemanaEnum = DiaSemanaEnum.TERCA;
			break;
		}
		case 2: {
			diaSemanaEnum = DiaSemanaEnum.QUARTA;
			break;
		}
		case 3: {
			diaSemanaEnum = DiaSemanaEnum.QUINTA;
			break;
		}
		case 4: {
			diaSemanaEnum = DiaSemanaEnum.SEXTA;
			break;
		}
		case 5: {
			diaSemanaEnum = DiaSemanaEnum.SABADO;
			break;
		}
		case 6: {
			diaSemanaEnum = DiaSemanaEnum.DOMINGO;
			break;
		}

		}
		return diaSemanaEnum;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
