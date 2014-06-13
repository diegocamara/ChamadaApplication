package com.application.chamada.dao;



import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.application.chamada.domain.Disciplina;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class AbstractORMLiteHelper<E> extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "chamada.db";

	private static final String DATABASE_CATEGORY = "database";

	private static final int DATABASE_VERSION = 1;

	private static AbstractORMLiteHelper mInstance;

	private Dao<E, Integer> abstractDAO;

	private RuntimeExceptionDao<E, Integer> abstractRuntimeDAO;

	private Class<E> persistentClass;

	public AbstractORMLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		
		try {
			TableUtils.createTableIfNotExists(connectionSource,
					getPersistentClass());			
		} catch (SQLException ex) {
			throw new RuntimeException();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, getPersistentClass(), true);
			onCreate(db, connectionSource);
		} catch (SQLException ex) {

		}
	}

	public Dao<E, Integer> getDAO() throws SQLException {

		if (this.abstractDAO == null) {
			this.abstractDAO = getDao(getPersistentClass());
		}

		return this.abstractDAO;

	}

	public RuntimeExceptionDao<E, Integer> getRuntimeDAO() {
		
		if (this.abstractRuntimeDAO == null) {
			this.abstractRuntimeDAO = getRuntimeExceptionDao(getPersistentClass());
		}

		return this.abstractRuntimeDAO;

	}

	private Class<E> getPersistentClass() {
		return this.persistentClass;
	}

}
