package com.application.chamada.listener;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.application.chamada.fragment.CadastrarHorarioFragment;

public class CheckBoxListener extends FragmentActivity implements
		OnClickListener {

	private FragmentManager currentFragmentManager;
	private Fragment fragment;
	private Context context;
	private int fragmentContainerId;

	public CheckBoxListener(FragmentManager fragmentManager,
			Context context, int fragmentContainerId) {
		setCurrentFragmentManager(fragmentManager);		
		setContext(context);
		setFragmentContainerId(fragmentContainerId);
	}

	@Override
	public void onClick(View v) {
		
	}

	public FragmentManager getCurrentFragmentManager() {
		return currentFragmentManager;
	}

	public void setCurrentFragmentManager(FragmentManager currentFragmentManager) {
		this.currentFragmentManager = currentFragmentManager;
	}

	public Fragment getFragment() {
		return fragment;
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}

	public int getFragmentContainerId() {
		return fragmentContainerId;
	}

	public void setFragmentContainerId(int fragmentContainerId) {
		this.fragmentContainerId = fragmentContainerId;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
