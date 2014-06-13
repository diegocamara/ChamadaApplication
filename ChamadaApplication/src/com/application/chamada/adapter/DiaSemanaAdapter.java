package com.application.chamada.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.domain.DiaSemana;

public class DiaSemanaAdapter extends BaseAdapter {

	private Context context;
	private List<DiaSemana> dias;

	public DiaSemanaAdapter(Context context, List<DiaSemana> dias) {
		setContext(context);
		setDias(dias);
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

		DiaSemana diaSemana = getDias().get(position);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = (View) inflater.inflate(R.layout.dia_semana_layout, null);

		CheckBox checkBox = (CheckBox) view.findViewById(R.id.diaCheckBox);
		TextView textView = (TextView) view.findViewById(R.id.textoCheckBox);

		checkBox.setChecked(diaSemana.isCheck());
		textView.setText(diaSemana.getDia().getDescricao());
		
		return view;
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

}
