package com.application.chamada.manager;

import android.content.Context;

public abstract class AbstractManager {

	public abstract void inicializarDAO(Context context);

	public abstract Object getDAO();

}
