package com.application.chamada.fragment;

import java.util.List;

import org.joda.time.DateTime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.application.chamada.R;
import com.application.chamada.adapter.DisciplinaDetalhesAdapter;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.manager.DisciplinaManager;
import com.application.chamada.manager.IDisciplinaManager;
import com.application.chamada.manager.IHorarioManager;
import com.application.chamada.manager.IProfessorManager;

public class DisciplinaDiaTabFragment extends Fragment {

	private IDisciplinaManager disciplinaManager;
	private IHorarioManager horarioManager;
	private IProfessorManager professorManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View tabView = inflater.inflate(R.layout.disciplinas_dia_layout,
				container, false);
		ListView disciplinas = (ListView) tabView
				.findViewById(R.id.disciplinaListView);


		Disciplina disciplina = new Disciplina();
		disciplina.setDataInicio(DateTime.now());
		disciplina.setDataFim(DateTime.parse("2014-08-05"));
		disciplina.setNome("Disciplina Fabulosa");
		
		disciplinaManager = new DisciplinaManager(getActivity());
		disciplinaManager.salvarOuAtualizar(disciplina);
		
		List<Disciplina> disciplinasDoDia = disciplinaManager.consultarDisciplinasDoDia(DateTime.now());		
		
		disciplinas.setAdapter(new DisciplinaDetalhesAdapter(getActivity(),
				disciplinasDoDia));

		return tabView;
	}

}
