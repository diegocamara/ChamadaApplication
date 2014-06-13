package com.application.chamada.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.application.chamada.R;
import com.application.chamada.activity.CadastrarDisciplinaActivity;

public class DisciplinasCadastradasFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {	
		
		View view = (View)inflater.inflate(R.layout.disciplinas_cadastradas_layout, null);
		
		Button addDisciplinaButton = (Button) view.findViewById(R.id.addDisciplinaButton);
		
		addDisciplinaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity(), CadastrarDisciplinaActivity.class);				
				startActivity(intent);
			}
		});
		
		
		return view;
	}

}
