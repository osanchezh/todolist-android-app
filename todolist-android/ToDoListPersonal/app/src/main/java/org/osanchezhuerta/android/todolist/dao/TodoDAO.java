package org.osanchezhuerta.android.todolist.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.osanchezhuerta.android.todolist.database.TodoDatabaseHelper;

/**
 * Created by sanchezocth on 30/08/2016.
 */
public class TodoDAO {
    protected SQLiteDatabase database;
    private TodoDatabaseHelper todoDatabaseHelper;
    private Context context;

    public TodoDAO(Context context) {
        this.context = context;
        this.todoDatabaseHelper = TodoDatabaseHelper.getHelper(context);
    }

    public void openWrite() throws SQLException {
        if(todoDatabaseHelper == null) {
            todoDatabaseHelper = TodoDatabaseHelper.getHelper(context);
        }
        database = todoDatabaseHelper.getWritableDatabase();
    }
    public void openRead() throws SQLException {
        if(todoDatabaseHelper == null)
            todoDatabaseHelper = todoDatabaseHelper.getHelper(context);
        database = todoDatabaseHelper.getReadableDatabase();
    }


    public void deleteDatabase(){
        context.deleteDatabase(TodoDatabaseHelper.DATABASE_NAME);
    }

    public void close() {
        if(todoDatabaseHelper!=null) {
            todoDatabaseHelper.close();
            database = null;
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase sqliteDatabase) {
        this.database = sqliteDatabase;
    }

    public TodoDatabaseHelper getTodoDatabaseHelper() {
        return todoDatabaseHelper;
    }

    public void setTodoDatabaseHelper(TodoDatabaseHelper todoDatabaseHelper) {
        this.todoDatabaseHelper = todoDatabaseHelper;
    }

}
