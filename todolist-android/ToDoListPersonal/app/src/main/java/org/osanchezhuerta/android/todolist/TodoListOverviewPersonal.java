package org.osanchezhuerta.android.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.osanchezhuerta.android.todolist.service.TodoServiceImpl;
import org.osanchezhuerta.android.todolist.vo.ToDo;

public class TodoListOverviewPersonal extends AppCompatActivity {


    private TodoServiceImpl todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_overview_personal);
        todoService = new TodoServiceImpl(this);

    }

    public void onClick(View view){
        EditText etxtSumary= (EditText)this.findViewById(R.id.todo_edit_summary);
        EditText etxtDescription= (EditText)this.findViewById(R.id.todo_edit_description);
        String stxtSumary = etxtSumary.getText().toString();
        String stxtDescription = etxtDescription.getText().toString();
        Log.d("onClick","onClick.finish,etxtSumary="+stxtSumary);
        Log.d("onClick","onClick.finish,etxtDescription="+stxtDescription);

        if(makeToast(stxtSumary,stxtDescription)) {
            Log.d("onClick", "onClick.finish");
            ToDo toDo = new ToDo();
            toDo.setSummary(stxtSumary);
            toDo.setDescription(stxtDescription);
            todoService.insert(toDo);

            Log.d("obtenerTodo.size=", todoService.obtenerTodo().size()+"");
            setResult(RESULT_OK);
            finish();
        }
    }
    public void onClickCancelar(View view){
        setResult(RESULT_OK);
        finish();
    }

    private boolean makeToast(String sSumary, String sDescription) {
        boolean valid=true;
        //etxtSumary.getText().toString()
        if (TextUtils.isEmpty(sSumary)) {
            Toast.makeText(TodoListOverviewPersonal.this, "Please maintain a summary",
                    Toast.LENGTH_LONG).show();
            valid=false;
        }
        if (TextUtils.isEmpty(sDescription)) {
            Toast.makeText(TodoListOverviewPersonal.this, "Please maintain a description",
                    Toast.LENGTH_LONG).show();
            valid=false;

        }
        return valid;

    }

    public TodoServiceImpl getTodoService() {
        return todoService;
    }

    public void setTodoService(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

}
