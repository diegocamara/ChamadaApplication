package com.application.chamada.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.application.chamada.R;
import com.application.chamada.adapter.DisciplinaDetalhesAdapter;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.manager.DisciplinaManager;

public class DisciplinasCadastradasActivity extends FragmentActivity {

	private DisciplinaManager disciplinaManager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		inicializar(getApplicationContext());

		setContentView(R.layout.disciplinas_cadastradas_layout);

		ListView disciplinasListView = (ListView) findViewById(R.id.disciplinasCadastradasListView);

		List<Disciplina> disciplinas = getDisciplinaManager()
				.consultarDisciplinas();

		disciplinasListView.setAdapter(new DisciplinaDetalhesAdapter(
				getApplicationContext(), disciplinas));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.operacoes_disciplina_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.adicionarNovaDisciplinaIcon: {

			startActivity(new Intent(getApplicationContext(),
					CadastrarDisciplinasFrameActivity.class));
			break;
		}
		}

		return super.onOptionsItemSelected(item);
	}

	private void inicializar(Context context) {
		this.disciplinaManager = new DisciplinaManager(context);
	}

	public DisciplinaManager getDisciplinaManager() {
		return disciplinaManager;
	}

	public void setDisciplinaManager(DisciplinaManager disciplinaManager) {
		this.disciplinaManager = disciplinaManager;
	}

}
