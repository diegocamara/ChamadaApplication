package com.application.chamada.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.Context;

import com.application.chamada.domain.Disciplina;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class DisciplinaDAO extends AbstractORMLiteHelper<Disciplina> {

	public DisciplinaDAO(Context context) {
		super(context);
	}

	public List<Disciplina> consultarDisciplinasDoDia(DateTime dataAtual) {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();

		QueryBuilder<Disciplina, Integer> queryBuilder = this.getRuntimeDAO()
				.queryBuilder();

		try {
			queryBuilder.where().lt(Disciplina.DATA_INICIO, dataAtual).and()
					.gt(Disciplina.DATA_FIM, dataAtual);
			PreparedQuery<Disciplina> preparedQuery = queryBuilder.prepare();
			disciplinas = this.getRuntimeDAO().query(preparedQuery);
		} catch (SQLException ex) {
		}

		return disciplinas;
	}

	public List<Disciplina> consultarDisciplinas() {
		return this.getRuntimeDAO().queryForAll();
	}

}
