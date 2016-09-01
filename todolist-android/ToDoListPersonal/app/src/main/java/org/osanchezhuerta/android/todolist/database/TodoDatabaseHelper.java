package org.osanchezhuerta.android.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "todotable.db";
	public static final int DATABASE_VERSION = 5;
	private static TodoDatabaseHelper instance;

	public TodoDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static synchronized TodoDatabaseHelper getHelper(Context context) {
		if (instance == null)
			instance = new TodoDatabaseHelper(context);
		return instance;
	}


	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		TodoTable.onCreate(database);
	}

	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
						  int newVersion) {
		TodoTable.onUpgrade(database, oldVersion, newVersion);
	}

	public static TodoDatabaseHelper getInstance() {
		return instance;
	}

	public static void setInstance(TodoDatabaseHelper instance) {
		TodoDatabaseHelper.instance = instance;
	}

}
