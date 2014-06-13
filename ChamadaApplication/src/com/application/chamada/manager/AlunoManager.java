package com.application.chamada.manager;

import android.content.Context;

import com.application.chamada.dao.AlunoDAO;
import com.application.chamada.domain.Aluno;

public class AlunoManager extends AbstractManager implements IAlunoManager {

	private AlunoDAO alunoDAO;

	public AlunoManager(Context context) {
		inicializarDAO(context);
	}

	@Override
	public void salvarOuAtualizar(Aluno aluno) {
		alunoDAO.getRuntimeDAO().createOrUpdate(aluno);
	}

	@Override
	public void inicializarDAO(Context context) {
		this.alunoDAO = new AlunoDAO(context);
	}

	@Override
	public Object getDAO() {
		return this.alunoDAO;
	}

}
