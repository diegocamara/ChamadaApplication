package com.application.chamada.manager;

import android.content.Context;

import com.application.chamada.dao.ProfessorDAO;
import com.application.chamada.domain.Professor;

public class ProfessorManager extends AbstractManager implements
		IProfessorManager {

	private ProfessorDAO professorDAO;

	public ProfessorManager(Context context){
		inicializarDAO(context);
	}
	
	@Override
	public void salvarOuAlterar(Professor professor) {
		professorDAO.getRuntimeDAO().createOrUpdate(professor);
		
	}

	@Override
	public void inicializarDAO(Context context) {
		this.professorDAO = new ProfessorDAO(context);
	}

	@Override
	public Object getDAO() {
		return this.professorDAO;
	}

}
