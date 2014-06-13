package com.application.chamada.activity;

import org.joda.time.DateTime;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.application.chamada.R;
import com.application.chamada.adapter.DiaSemanaAdapter;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.domain.Horario;
import com.application.chamada.fragment.DateDialog;
import com.application.chamada.manager.DisciplinaManager;
import com.application.chamada.manager.HorarioManager;
import com.application.chamada.manager.IHorarioManager;
import com.application.chamada.util.DiaSemanaUtil;

public class CadastrarDisciplinaActivity extends FragmentActivity {

	private Disciplina disciplina;

	private DisciplinaManager disciplinaManager;
	private IHorarioManager horarioManager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.cadastrar_disciplina_layout);

		TextView textDataInicioNaoDefinida = (TextView) findViewById(R.id.dataInicioNaoDefinida);
		textDataInicioNaoDefinida.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DateDialog dateDialog = new DateDialog();
				dateDialog.show(getSupportFragmentManager(),
						"datepicker_dialog_fragment");
			}
		});

		TextView textDataFimNaoDefinida = (TextView) findViewById(R.id.dataFimNaoDefinida);

		textDataFimNaoDefinida.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		ListView diasSemana = (ListView) findViewById(R.id.diasSemanaCheckBoxesList);

		diasSemana.setAdapter(new DiaSemanaAdapter(getApplicationContext(),
				DiaSemanaUtil.obterDiasSemanaPadrao()));

		this.disciplinaManager = new DisciplinaManager(getApplicationContext());
		this.horarioManager = new HorarioManager(getApplicationContext());

		this.disciplina = new Disciplina();
		this.disciplina.setNome("Disciplina 1");
		this.disciplina.setDataInicio(new DateTime());
		this.disciplina.setDataFim(new DateTime());

		this.disciplinaManager.salvarOuAtualizar(disciplina);
		
		
		
		Horario horario = new Horario();
		//horario.setDia(DiaSemanaEnum.QUARTA);
		//horario.setDisciplina(disciplinaManager.find(disciplina));
		
		this.horarioManager.salvarOuAtualizar(horario);
		
		

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

}
