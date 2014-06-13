package com.application.chamada.dao;

import android.content.Context;

import com.application.chamada.domain.Aluno;

public class AlunoDAO extends AbstractORMLiteHelper<Aluno>{

	public AlunoDAO(Context context) {
		super(context);		
	}

}
