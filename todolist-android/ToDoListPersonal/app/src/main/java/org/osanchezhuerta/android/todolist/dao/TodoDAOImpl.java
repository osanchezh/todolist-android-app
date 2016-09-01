package org.osanchezhuerta.android.todolist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.osanchezhuerta.android.todolist.database.TodoDatabaseHelper;
import org.osanchezhuerta.android.todolist.database.TodoTable;
import org.osanchezhuerta.android.todolist.vo.ToDo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sanchezocth on 30/08/2016.
 */
public class TodoDAOImpl extends TodoDAO {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static final String WHERE_ID_EQUALS = TodoTable.COLUMN_ID+ " =?";;

    public TodoDAOImpl(Context context) {
        super(context);
    }

    public ArrayList<ToDo> obtenerTodo() {
        ArrayList<ToDo> lstTodo = new ArrayList<ToDo>();

        Cursor cursor = database.query(TodoTable.TABLE_TODO,
                new String[] { TodoTable.COLUMN_ID,
                        //TodoTable.COLUMN_CATEGORY,
                        TodoTable.COLUMN_SUMMARY,
                        TodoTable.COLUMN_DESCRIPTION}, null, null, null,
                null, null);
        while (cursor.moveToNext()) {
            ToDo todo = new ToDo();
            todo.setId(cursor.getInt(0));
            //todo.setCategory(cursor.getString(1));
            todo.setSummary(cursor.getString(1));
            todo.setDescription(cursor.getString(2));
            lstTodo.add(todo);
        }
        cursor.close();
        return lstTodo;
    }

    public long insert(ToDo todo) {
        ContentValues values = new ContentValues();
        //values.put(TodoTable.COLUMN_CATEGORY, todo.getCategory());
        values.put(TodoTable.COLUMN_DESCRIPTION, todo.getDescription());
        values.put(TodoTable.COLUMN_SUMMARY, todo.getSummary());

        return database.insert(TodoTable.TABLE_TODO, null, values);
    }

    public long update(ToDo todo) {
        ContentValues values = new ContentValues();

        values.put(TodoTable.COLUMN_SUMMARY, todo.getSummary());
        values.put(TodoTable.COLUMN_DESCRIPTION, todo.getDescription());

        long result = database.update("todo", values,
                WHERE_ID_EQUALS, new String[] { String.valueOf(todo.getId())} );


        Log.d("Update Result:", "=" + result);
        return result;

    }
}
