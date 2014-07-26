package com.application.chamada.fragment;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.chamada.R;
import com.application.chamada.adapter.DiaSemanaAdapter;
import com.application.chamada.domain.DiaSemanaEnum;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.domain.Horario;
import com.application.chamada.manager.HorarioManager;
import com.application.chamada.manager.IHorarioManager;
import com.application.chamada.util.DiaSemanaUtil;

public class CadastrarHorarioFragment extends Fragment {

	private static final int ZERO = 0;
	private Disciplina disciplina;
	private ListView dias;
	private Map<DiaSemanaEnum, Horario> horarios;
	private IHorarioManager horarioManager;

	public static CadastrarHorarioFragment newInstance(Disciplina disciplina) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(Disciplina.DISCIPLINA_KEY, disciplina);
		CadastrarHorarioFragment cadastrarHorarioFragment = new CadastrarHorarioFragment();
		cadastrarHorarioFragment.setArguments(bundle);
		return cadastrarHorarioFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inicializar(getActivity());

		configurarDisciplina(getArguments());

		setHasOptionsMenu(true);

		View view = (View) inflater.inflate(
				R.layout.cadastrar_dias_semana_layout, container, false);
		dias = getView(R.id.diasSemanaCheckBoxesList, view);

		dias.setAdapter(new DiaSemanaAdapter(getActivity(), DiaSemanaUtil
				.obterDiasSemanaPadrao(), getFragmentManager(),
				CadastrarHorarioFragment.this, horarios));

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuInflater menuInflater = getActivity().getMenuInflater();
		menu.clear();
		menuInflater.inflate(R.menu.cadastrar_dia_semana_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.confirmarHorario: {
			if(validarHorarios()){
				salvarHorarios();
				getActivity().finish();
			}else{
				Toast.makeText(getActivity(), "Existem horarios indefinidos!", Toast.LENGTH_SHORT).show();
			}
			break;
		}
		}

		return super.onOptionsItemSelected(item);
	}

	private boolean validarHorarios() {

		boolean resultado = true;
		
		if (getHorarios().size() > 0) {

			for (Map.Entry<DiaSemanaEnum, Horario> entry : getHorarios()
					.entrySet()) {

				Horario horario = entry.getValue();

				if (isNull(horario.getDia())
						|| isNull(horario.getHoraInicio())
						|| isNull(horario.getHoraFim())) {					
					resultado = false;
					break;
				}

			}

		}else{
			resultado = false;
		}
		
		
		return resultado;
	}

	private void salvarHorarios(){
		for(Map.Entry<DiaSemanaEnum, Horario> entry : getHorarios().entrySet()){
			getHorarioManager().salvarOuAtualizar(entry.getValue());
			Toast.makeText(getActivity(), disciplina.getNome() + " salva." , Toast.LENGTH_SHORT).show();
		}
	}
	
	private void configurarDisciplina(Bundle bundle) {

		if (isNotNull(bundle)) {
			this.disciplina = (Disciplina) bundle
					.getSerializable(Disciplina.DISCIPLINA_KEY);
		}

	}

	private void configurarListaHorario(int hora, int minutos, int position,
			int requestCode) {

		Horario horario = new Horario();
		
		if(getHorarios().get(DiaSemanaEnum.obterDiaSemanaPorIndex(position)) != null){
			horario = getHorarios().get(DiaSemanaEnum.obterDiaSemanaPorIndex(position));
		}
		
		horario.setDisciplina(disciplina);
		horario.setDia(DiaSemanaEnum.obterDiaSemanaPorIndex(position));

		int ano = DateTime.now().getYear();
		int mes = DateTime.now().getMonthOfYear();
		int dia = DateTime.now().getDayOfMonth();

		DateTime dateTime = new DateTime(ano, mes, dia, hora, minutos);

		switch (requestCode) {
		case HourDialog.HORA_INICIO_REQUEST_CODE: {
			horario.setHoraInicio(dateTime);
			break;
		}
		case HourDialog.HORA_FIM_REQUEST_CODE: {
			horario.setHoraFim(dateTime);
			break;
		}
		}		
		
		getHorarios().put(horario.getDia(), horario);

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

		configurarListaHorario(hora, minutos, position, requestCode);

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void inicializar(Context context) {
		this.horarios = new HashMap<DiaSemanaEnum, Horario>();
		this.horarioManager = new HorarioManager(context);
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

		CheckBox checkBox = getView(R.id.diaCheckBox, view);
		checkBox.setChecked(true);
	}

	private <Type> Type getView(int viewId, View view) {
		return (Type) view.findViewById(viewId);
	}

	public boolean isNotNull(Object object) {
		return object != null;
	}
	
	public boolean isNull(Object object){
		return object == null;
	}

	public ListView getDias() {
		return dias;
	}

	public void setDias(ListView dias) {
		this.dias = dias;
	}

	public Map<DiaSemanaEnum, Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(Map<DiaSemanaEnum, Horario> horarios) {
		this.horarios = horarios;
	}

	public IHorarioManager getHorarioManager() {
		return horarioManager;
	}

	public void setHorarioManager(IHorarioManager horarioManager) {
		this.horarioManager = horarioManager;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
