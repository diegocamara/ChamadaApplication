package com.application.chamada.domain;

public class DiaSemana {

	private DiaSemanaEnum dia;
	private boolean check;

	public DiaSemana(DiaSemanaEnum diaSemanaEnum, boolean isCheck){
		setDia(diaSemanaEnum);
		setCheck(isCheck);
	}
	
	public DiaSemanaEnum getDia() {
		return dia;
	}

	public void setDia(DiaSemanaEnum dia) {
		this.dia = dia;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
