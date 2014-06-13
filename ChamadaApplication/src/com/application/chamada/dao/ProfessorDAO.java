package com.application.chamada.dao;

import android.content.Context;

import com.application.chamada.domain.Professor;

public class ProfessorDAO extends AbstractORMLiteHelper<Professor>{

	public ProfessorDAO(Context context) {
		super(context);		
	}

}
