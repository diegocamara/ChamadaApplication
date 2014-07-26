package com.application.chamada.manager;

import java.util.List;

import org.joda.time.DateTime;

import com.application.chamada.domain.Disciplina;

public interface IDisciplinaManager {	
	
	void salvarOuAtualizar(Disciplina disciplina);
	
	Disciplina find(Disciplina disciplina);
	
	List<Disciplina> consultarDisciplinasDoDia(DateTime dataAtual);
	
	List<Disciplina> consultarDisciplinas();
}
