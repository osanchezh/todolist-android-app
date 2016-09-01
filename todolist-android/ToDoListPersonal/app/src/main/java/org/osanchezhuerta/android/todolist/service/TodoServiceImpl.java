package org.osanchezhuerta.android.todolist.service;

import android.content.Context;
import android.util.Log;

import org.osanchezhuerta.android.todolist.dao.TodoDAOImpl;
import org.osanchezhuerta.android.todolist.vo.ToDo;

import java.util.ArrayList;

/**
 * Created by sanchezocth on 30/08/2016.
 */
public class TodoServiceImpl {

    private TodoDAOImpl todoDAO;

    public TodoServiceImpl(Context context) {
        todoDAO = new TodoDAOImpl(context);
    }
    public ArrayList<ToDo> obtenerTodo() {
        ArrayList<ToDo> lstEstado = new ArrayList<ToDo>(0);
        todoDAO.openRead();
        try {
            todoDAO.getDatabase().beginTransaction();
            lstEstado.addAll(todoDAO.obtenerTodo());
            todoDAO.getDatabase().setTransactionSuccessful();
        }catch(Exception exception) {
            Log.e("obtenerEstados",exception.getMessage(),exception);
        }finally {
            todoDAO.getDatabase().endTransaction();
            todoDAO.close();
        }
        return lstEstado;
    }

    public long insert(ToDo todo){
        todoDAO.openWrite();
        long resultado=0L;
        try {
            todoDAO.getDatabase().beginTransaction();
            resultado = todoDAO.insert(todo);
            Log.d("insertar.todo=",todo.toString());
            todoDAO.getDatabase().setTransactionSuccessful();
        }catch(Exception exception) {
            Log.e("obtenerEstados",exception.getMessage(),exception);
        }finally {
            todoDAO.getDatabase().endTransaction();
            todoDAO.close();
        }
        return resultado;
    }

    public long update(ToDo todo){
        todoDAO.openWrite();
        long resultado=0L;
        try {
            todoDAO.getDatabase().beginTransaction();
            resultado = todoDAO.update(todo);
            Log.d("insertar.todo=",todo.toString());
            todoDAO.getDatabase().setTransactionSuccessful();
        }catch(Exception exception) {
            Log.e("obtenerEstados",exception.getMessage(),exception);
        }finally {
            todoDAO.getDatabase().endTransaction();
            todoDAO.close();
        }
        return resultado;
    }

    public TodoDAOImpl getTodoDAO() {
        return todoDAO;
    }

    public void setTodoDAO(TodoDAOImpl todoDAO) {
        this.todoDAO = todoDAO;
    }

}
