package com.application.chamada.activity;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.application.chamada.R;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.domain.Horario;
import com.application.chamada.domain.Professor;
import com.application.chamada.util.DataBaseUtil;

public class MainActivity extends FragmentActivity {	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		criarTabelas();		
		setContentView(R.layout.disciplinas_dia_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_menu_layout, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.cadastrarDisciplina: {
			
			startActivity(new Intent(getApplicationContext(), DisciplinasCadastradasActivity.class));
						
			break;
		}
		}

		return super.onOptionsItemSelected(item);
	}

	private void criarTabelas() {
		new DataBaseUtil(getApplicationContext(), Arrays.asList(
				Disciplina.class, Horario.class, Professor.class))
				.getWritableDatabase();
	}


}
