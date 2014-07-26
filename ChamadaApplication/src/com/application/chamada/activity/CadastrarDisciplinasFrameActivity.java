package com.application.chamada.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.application.chamada.R;
import com.application.chamada.fragment.CadastrarDisciplinaFragment;

public class CadastrarDisciplinasFrameActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle arg0) {		
		super.onCreate(arg0);
		setContentView(R.layout.disciplinas_cadastradas_frame_container);
		
		startFragment(CadastrarDisciplinaFragment.newInstance(),
				R.id.fragmentContainer);
		
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
