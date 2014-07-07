package com.application.chamada.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.adapter.DiaSemanaAdapter;
import com.application.chamada.util.DiaSemanaUtil;

public class CadastrarHorarioFragment extends Fragment {

	private static final int ZERO = 0;
	private ListView dias;
	private TextView horaInicio;
	private TextView horaFim;

	public static CadastrarHorarioFragment newInstance() {
		Bundle bundle = new Bundle();
		CadastrarHorarioFragment cadastrarHorarioFragment = new CadastrarHorarioFragment();
		cadastrarHorarioFragment.setArguments(bundle);
		return cadastrarHorarioFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setHasOptionsMenu(true);

		View view = (View) inflater.inflate(
				R.layout.cadastrar_dias_semana_layout, container, false);
		dias = getView(R.id.diasSemanaCheckBoxesList, view);

		dias.setAdapter(new DiaSemanaAdapter(getActivity(), DiaSemanaUtil
				.obterDiasSemanaPadrao(), getFragmentManager(),
				CadastrarHorarioFragment.this));

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuInflater menuInflater = getActivity().getMenuInflater();
		menu.clear();
		menuInflater.inflate(R.menu.cadastrar_disciplina_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		int position = ZERO;
		int hora = ZERO;
		int minutos = ZERO;

		if (isNotNull(data)) {
			Bundle bundle = new Bundle();
			if (isNotNull(data.getExtras())) {
				bundle = data.getExtras();
				position = bundle.getInt(HourDialog.POSITION);
				hora = data.getIntExtra(HourDialog.HORA, ZERO);
				minutos = data.getIntExtra(HourDialog.MINUTOS, ZERO);
			}
		}

		View view = (View) dias.getChildAt(position);
		
		configurarHora(hora, minutos, view, requestCode);

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void configurarHora(int hora, int minutos, View view,
			int requestCode) {

		switch (requestCode) {
		case HourDialog.HORA_INICIO_REQUEST_CODE: {

			TextView horaInicio = getView(R.id.horaInicioId, view);
			horaInicio.setText(hora + ":" + minutos);

			break;
		}
		case HourDialog.HORA_FIM_REQUEST_CODE: {
			TextView horaFim = getView(R.id.horaFimId, view);
			horaFim.setText(hora + ":" + minutos);
			break;
		}
		}

	}

	private <Type> Type getView(int viewId, View view) {

		return (Type) view.findViewById(viewId);
	}

	public boolean isNotNull(Object object) {
		return object != null;
	}

}
