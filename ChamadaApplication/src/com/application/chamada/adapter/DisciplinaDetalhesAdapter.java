package com.application.chamada.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.util.DateUtils;

public class DisciplinaDetalhesAdapter extends BaseAdapter {

	private Context context;
	private List<Disciplina> disciplinas;

	public DisciplinaDetalhesAdapter(Context context,
			List<Disciplina> disciplinas) {
		setContext(context);
		setDisciplinas(disciplinas);
	}

	@Override
	public int getCount() {
		return disciplinas.size();
	}

	@Override
	public Object getItem(int position) {
		return disciplinas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Disciplina disciplina = getDisciplinas().get(position);
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		
		View view = (View) inflater.inflate(R.layout.disciplina_list_layout, null);		

		TextView nomeDisciplina = (TextView)view.findViewById(R.id.nome_disciplina);
		TextView horaInicio = (TextView)view.findViewById(R.id.horaInicio);
		TextView horaFim = (TextView)view.findViewById(R.id.horaFim);
						
		nomeDisciplina.setText(disciplina.getNome());
		horaInicio.setText(DateUtils.obterDescricaoData(disciplina.getDataInicio()));
		horaFim.setText(DateUtils.obterDescricaoData(disciplina.getDataFim()));
		
		return view;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
