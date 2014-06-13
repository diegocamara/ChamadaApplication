package com.application.chamada.manager;

import android.content.Context;

import com.application.chamada.dao.HorarioDAO;
import com.application.chamada.domain.Horario;

public class HorarioManager extends AbstractManager implements IHorarioManager{

	private HorarioDAO horarioDAO;
	
	public HorarioManager(Context context){
		inicializarDAO(context);
	}
	
	@Override
	public void salvarOuAtualizar(Horario horario) {
		this.horarioDAO.getRuntimeDAO().createOrUpdate(horario);		
	}

	@Override
	public void inicializarDAO(Context context) {
		this.horarioDAO = new HorarioDAO(context);		
	}

	@Override
	public Object getDAO() {		
		return this.horarioDAO;
	}

}
