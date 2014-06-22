package com.application.chamada.fragment;

import org.joda.time.DateTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.adapter.DiaSemanaAdapter;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.manager.DisciplinaManager;
import com.application.chamada.manager.IHorarioManager;
import com.application.chamada.util.DateUtils;
import com.application.chamada.util.DiaSemanaUtil;

public class CadastrarDisciplinaFragment extends Fragment {

	private static final int DATA_INICIO_REQUEST_CODE = 1;
	private static final int DATA_FIM_REQUEST_CODE = 2;
	private static final String DATEPICKER_DIALOG_FRAGMENT_TAG = "datepicker_dialog_fragment";

	private TextView textDataInicioNaoDefinida;
	private TextView textDataFimNaoDefinida;

	private Disciplina disciplina;

	private DisciplinaManager disciplinaManager;
	private IHorarioManager horarioManager;

	public static CadastrarDisciplinaFragment newInstance() {
		Bundle bundle = new Bundle();
		CadastrarDisciplinaFragment cadastrarDisciplinaFragment = new CadastrarDisciplinaFragment();
		cadastrarDisciplinaFragment.setArguments(bundle);
		return cadastrarDisciplinaFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inicializar();

		View view = (View) inflater.inflate(
				R.layout.cadastrar_disciplina_layout, container, false);

		textDataInicioNaoDefinida = (TextView) view
				.findViewById(R.id.dataInicioNaoDefinida);
		textDataInicioNaoDefinida.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATA_INICIO_REQUEST_CODE);
			}

		});

		textDataFimNaoDefinida = (TextView) view
				.findViewById(R.id.dataFimNaoDefinida);

		textDataFimNaoDefinida.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DATA_FIM_REQUEST_CODE);
			}
		});

		ListView diasSemana = (ListView) view
				.findViewById(R.id.diasSemanaCheckBoxesList);

		diasSemana.setAdapter(new DiaSemanaAdapter(getActivity(), DiaSemanaUtil
				.obterDiasSemanaPadrao()));

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		DateTime dataRetorno = null;

		if (data != null) {

			Bundle bundle = data.getExtras();

			if (bundle != null) {
				dataRetorno = (DateTime) bundle
						.getSerializable(DateDialog.DATA);
			}

		}

		switch (requestCode) {
		case DATA_INICIO_REQUEST_CODE: {
			this.disciplina.setDataInicio(dataRetorno);
			this.textDataInicioNaoDefinida.setText(DateUtils
					.obterDescricaoData(dataRetorno));
			break;
		}
		case DATA_FIM_REQUEST_CODE: {
			this.disciplina.setDataFim(dataRetorno);
			this.textDataFimNaoDefinida.setText(DateUtils
					.obterDescricaoData(dataRetorno));
			break;
		}
		}

	}

	private void showDialog(int requestCode) {
		DateDialog dateDialog = DateDialog.newInstance(obterDataInicioDisciplina(requestCode));
		dateDialog.setTargetFragment(CadastrarDisciplinaFragment.this,
				requestCode);
		dateDialog.show(getFragmentManager(), DATEPICKER_DIALOG_FRAGMENT_TAG);
	}

	private DateTime obterDataInicioDisciplina(int requestCode) {

		DateTime dataRetorno = DateTime.now();
		
		switch (requestCode) {
		case DATA_INICIO_REQUEST_CODE: {
			
			if (this.disciplina.getDataInicio() != null) {
				dataRetorno = this.disciplina.getDataInicio();
			}

			break;
		}
		case DATA_FIM_REQUEST_CODE:{
			
			if(this.disciplina.getDataFim() != null){
				dataRetorno = this.disciplina.getDataFim();
			}
			
			break;
		}
		}

		return dataRetorno;
		
	}

	private void inicializar() {
		this.disciplina = new Disciplina();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public DisciplinaManager getDisciplinaManager() {
		return disciplinaManager;
	}

	public void setDisciplinaManager(DisciplinaManager disciplinaManager) {
		this.disciplinaManager = disciplinaManager;
	}

	public IHorarioManager getHorarioManager() {
		return horarioManager;
	}

	public void setHorarioManager(IHorarioManager horarioManager) {
		this.horarioManager = horarioManager;
	}

}
