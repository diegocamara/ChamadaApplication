package com.application.chamada.util;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseUtil extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "chamada.db";

	private static final String DATABASE_CATEGORY = "database";

	private static final int DATABASE_VERSION = 1;

	private List<Class<? extends Object>> entitys;

	public DataBaseUtil(Context context, List<Class<? extends Object>> list) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		setEntitys(list);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		Log.i(DATABASE_CATEGORY, "onCreate() called");
		try {
			for (Class clazz : entitys) {
				TableUtils.createTableIfNotExists(connectionSource, clazz);
			}
		} catch (SQLException ex) {
			throw new RuntimeException();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			for (Class clazz : entitys) {
				TableUtils.dropTable(connectionSource, clazz, true);
			}
			onCreate(db, connectionSource);
		} catch (SQLException ex) {

		}
	}

	public List<Class<? extends Object>> getEntitys() {
		return entitys;
	}

	public void setEntitys(List<Class<? extends Object>> entitys) {
		this.entitys = entitys;
	}

	

}
