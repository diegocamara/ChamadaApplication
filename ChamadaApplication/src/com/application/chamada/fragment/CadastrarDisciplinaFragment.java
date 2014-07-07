package com.application.chamada.fragment;

import org.joda.time.DateTime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.manager.DisciplinaManager;
import com.application.chamada.manager.IHorarioManager;
import com.application.chamada.util.DateUtils;

public class CadastrarDisciplinaFragment extends Fragment {

	private static final int DATA_INICIO_REQUEST_CODE = 1;
	private static final int DATA_FIM_REQUEST_CODE = 2;
	private static final String DATEPICKER_DIALOG_FRAGMENT_TAG = "datepicker_dialog_fragment";
	public static final String DISCIPLINA = "DISCIPLINA";

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

		inicializar(getActivity());

		setHasOptionsMenu(true);

		View view = (View) inflater.inflate(
				R.layout.cadastrar_disciplina_layout, container, false);

		EditText nomeDisciplinaView = (EditText) view.findViewById(R.id.disciplinaNomeEditText);
		
		
		nomeDisciplinaView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				getDisciplina().setNome(s.toString());				
			}
		});
		
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
		DateDialog dateDialog = DateDialog
				.newInstance(obterDataInicioDisciplina(requestCode));
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
		case DATA_FIM_REQUEST_CODE: {

			if (this.disciplina.getDataFim() != null) {
				dataRetorno = this.disciplina.getDataFim();
			}

			break;
		}
		}

		return dataRetorno;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuInflater menuInflater = getActivity().getMenuInflater();
		menu.clear();
		menuInflater.inflate(R.menu.cadastrar_disciplina_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.confirmarDisciplina: {
			
			this.disciplinaManager.salvarOuAtualizar(disciplina);
			getArguments().putSerializable(DISCIPLINA, disciplina);
			
			startFragment(CadastrarHorarioFragment.newInstance(),
					R.id.fragmentContainer);
		}
		}

		return super.onOptionsItemSelected(item);
	}

	private void startFragment(Fragment fragment, int fragmentContainerId) {

		FragmentManager fragmentManager = getFragmentManager();
		Fragment fragmentContainer = fragmentManager
				.findFragmentById(fragmentContainerId);
		
			fragmentContainer = fragment;
			fragmentManager.beginTransaction()
					.replace(fragmentContainerId, fragmentContainer)
					.addToBackStack(null).commit();
		

	}

	private void inicializar(Context context) {
		this.disciplina = new Disciplina();
		this.disciplinaManager = new DisciplinaManager(context);
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
