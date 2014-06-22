package com.application.chamada.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.application.chamada.R;
import com.application.chamada.fragment.CadastrarDisciplinaFragment;

public class DisciplinasCadastradasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.disciplinas_cadastradas_layout);

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
			startFragment(CadastrarDisciplinaFragment.newInstance(),
					R.id.fragmentContainer);
			break;
		}
		}

		return super.onOptionsItemSelected(item);
	}

	private void startFragment(Fragment fragment, int fragmentContainerId) {

		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragmentContainer = fragmentManager
				.findFragmentById(fragmentContainerId);

		if (fragmentContainer == null) {
			fragmentContainer = fragment;
			fragmentManager.beginTransaction()
					.add(fragmentContainerId, fragmentContainer)
					.addToBackStack(null).commit();
		}

	}

}
