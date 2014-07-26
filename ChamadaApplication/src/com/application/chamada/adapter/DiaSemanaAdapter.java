package com.application.chamada.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.domain.DiaSemana;
import com.application.chamada.domain.DiaSemanaEnum;
import com.application.chamada.domain.Horario;
import com.application.chamada.fragment.CadastrarHorarioFragment;
import com.application.chamada.fragment.HourDialog;

public class DiaSemanaAdapter extends BaseAdapter {

	private Context context;
	private List<DiaSemana> dias;
	private Map<DiaSemanaEnum, Horario> horarios;
	private FragmentManager fragmentManager;
	private CadastrarHorarioFragment cadastrarHorarioFragment;

	public DiaSemanaAdapter(Context context, List<DiaSemana> dias,
			FragmentManager fragmentManager,
			CadastrarHorarioFragment cadastrarHorarioFragment, Map<DiaSemanaEnum, Horario> horarios) {
		setContext(context);
		setDias(dias);
		setFragmentManager(fragmentManager);
		setCadastrarHorarioFragment(cadastrarHorarioFragment);
		setHorarios(horarios);
	}

	@Override
	public int getCount() {
		return this.dias.size();
	}

	@Override
	public Object getItem(int position) {
		return dias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final int requestCodePosition = position;

		DiaSemana diaSemana = getDias().get(position);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = (View) inflater.inflate(R.layout.dia_semana_layout, null);

		CheckBox checkBox = (CheckBox) view.findViewById(R.id.diaCheckBox);
		TextView textView = (TextView) view.findViewById(R.id.textoCheckBox);
		TextView horaInicio = (TextView) view.findViewById(R.id.horaInicioId);
		TextView horaFim = (TextView) view.findViewById(R.id.horaFimId);

		horaInicio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(HourDialog.HORA_INICIO_REQUEST_CODE,
						requestCodePosition);
			}
		});

		horaFim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(HourDialog.HORA_FIM_REQUEST_CODE,
						requestCodePosition);
			}
		});

		checkBox.setChecked(diaSemana.isCheck());

		textView.setText(diaSemana.getDia().getDescricao());

		return view;
	}

	private void showDialog(int requestCode, int position) {
		HourDialog hourDialog = HourDialog.newInstance(position);
		hourDialog
				.setTargetFragment(getCadastrarHorarioFragment(), requestCode);
		hourDialog.show(getFragmentManager(), HourDialog.HORA_TAG);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<DiaSemana> getDias() {
		return dias;
	}

	public void setDias(List<DiaSemana> dias) {
		this.dias = dias;
	}

	public FragmentManager getFragmentManager() {
		return fragmentManager;
	}

	public void setFragmentManager(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public CadastrarHorarioFragment getCadastrarHorarioFragment() {
		return cadastrarHorarioFragment;
	}

	public void setCadastrarHorarioFragment(
			CadastrarHorarioFragment cadastrarHorarioFragment) {
		this.cadastrarHorarioFragment = cadastrarHorarioFragment;
	}

	public Map<DiaSemanaEnum, Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(Map<DiaSemanaEnum, Horario> horarios) {
		this.horarios = horarios;
	}

}
