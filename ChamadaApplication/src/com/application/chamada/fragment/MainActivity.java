package com.application.chamada.fragment;

import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.application.chamada.R;
import com.application.chamada.domain.Disciplina;
import com.application.chamada.domain.Horario;
import com.application.chamada.domain.Professor;
import com.application.chamada.util.DataBaseUtil;

public class MainActivity extends FragmentActivity{

	private FragmentTabHost mTabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.tab_host);
		
		criarTabelas();
		
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		
				
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		mTabHost.addTab(mTabHost.newTabSpec("Device1").setIndicator("Disciplinas do dia", getResources().getDrawable(R.drawable.ic_launcher)), DisciplinaDiaTabFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("Device2").setIndicator("Disciplinas cadastradas"), DisciplinasCadastradasFragment.class, null);
		
		
		
		
	}
	
	private void criarTabelas(){
		new DataBaseUtil(getApplicationContext(), Arrays.asList(Disciplina.class, Horario.class, Professor.class)).getWritableDatabase();
	}
	
}
