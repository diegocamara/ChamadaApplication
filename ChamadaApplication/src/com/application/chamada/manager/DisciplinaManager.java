package com.application.chamada.manager;

import java.util.List;

import org.joda.time.DateTime;

import android.content.Context;

import com.application.chamada.dao.DisciplinaDAO;
import com.application.chamada.domain.Disciplina;

public class DisciplinaManager extends AbstractManager implements IDisciplinaManager{

	private DisciplinaDAO disciplinaDAO;
	
	public DisciplinaManager(Context context){
		inicializarDAO(context);
	}
	
	@Override
	public void inicializarDAO(Context context) {
		this.disciplinaDAO = new DisciplinaDAO(context);		
	}

	@Override
	public Object getDAO() {		
		return this.disciplinaDAO;
	}

	@Override
	public void salvarOuAtualizar(Disciplina disciplina) {		
		this.disciplinaDAO.getRuntimeDAO().createOrUpdate(disciplina);		
	}
	
	@Override
	public Disciplina find(Disciplina disciplina){
		return this.disciplinaDAO.getRuntimeDAO().queryForSameId(disciplina);
	}

	@Override
	public List<Disciplina> consultarDisciplinasDoDia(DateTime dataAtual) {				
		return this.disciplinaDAO.consultarDisciplinasDoDia(dataAtual);
	}

}
