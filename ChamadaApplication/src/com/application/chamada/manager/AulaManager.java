package com.application.chamada.manager;

import android.content.Context;

import com.application.chamada.dao.AulaDAO;

public class AulaManager extends AbstractManager implements IAulaManager{

	private AulaDAO aulaDAO;
	
	public AulaManager(Context context){
		inicializarDAO(context);
	}
	
	@Override
	public void inicializarDAO(Context context) {
		this.aulaDAO = new AulaDAO(context);		
	}

	@Override
	public Object getDAO() {		
		return this.aulaDAO;
	}

}
