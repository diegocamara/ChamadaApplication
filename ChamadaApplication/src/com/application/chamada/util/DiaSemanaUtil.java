package com.application.chamada.util;

import java.util.ArrayList;
import java.util.List;

import com.application.chamada.domain.DiaSemana;
import com.application.chamada.domain.DiaSemanaEnum;

public class DiaSemanaUtil {

	public static List<DiaSemana> obterDiasSemanaPadrao(){
		
		List<DiaSemana> diasSemana = new ArrayList<DiaSemana>();
		diasSemana.add(new DiaSemana(DiaSemanaEnum.SEGUNDA, false));
		diasSemana.add(new DiaSemana(DiaSemanaEnum.TERCA, false));
		diasSemana.add(new DiaSemana(DiaSemanaEnum.QUARTA, false));
		diasSemana.add(new DiaSemana(DiaSemanaEnum.QUINTA, false));
		diasSemana.add(new DiaSemana(DiaSemanaEnum.SEXTA, false));
		diasSemana.add(new DiaSemana(DiaSemanaEnum.SABADO, false));
		diasSemana.add(new DiaSemana(DiaSemanaEnum.DOMINGO, false));
		
		return diasSemana;
	}
	
}
