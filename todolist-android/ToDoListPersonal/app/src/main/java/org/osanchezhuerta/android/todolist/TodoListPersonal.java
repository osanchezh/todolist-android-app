package org.osanchezhuerta.android.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.osanchezhuerta.android.todolist.adapter.TodoArrayAdapter;
import org.osanchezhuerta.android.todolist.fragment.ViewTodoDialogFragment;
import org.osanchezhuerta.android.todolist.service.TodoServiceImpl;
import org.osanchezhuerta.android.todolist.vo.ToDo;

import java.util.List;

public class TodoListPersonal extends AppCompatActivity {
    String msg = "TodoListPersonal : ";

    private TodoServiceImpl todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_personal);
        todoService = new TodoServiceImpl(this);
        fillData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
        fillData();
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }


    public void fillData() {

        final List<ToDo> lstTodo= todoService.obtenerTodo();
        Log.d("lstTodo.size=",lstTodo.size()+"");
        TodoArrayAdapter adapter = new TodoArrayAdapter(this,lstTodo.toArray(new ToDo[lstTodo.size()]));
        ListView listView = (ListView) findViewById(R.id.lvwTodo);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                  Log.d("item.selected=","position="+position);
                  ToDo todo = lstTodo.get(position);
                Bundle arguments = new Bundle();
                arguments.putParcelable("selectedToDo", todo);

                ViewTodoDialogFragment viewTodoDialogFragment = new ViewTodoDialogFragment();
                viewTodoDialogFragment.setArguments(arguments);
                viewTodoDialogFragment.show(getFragmentManager(),
                        ViewTodoDialogFragment.ARG_ITEM_ID);

            }
        });
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onSaveInstanceState=","XXXX");

        //outState.putParcelableArray("AristArray", mArtistArray);
    }

    // Create the menu based on the XML defintion
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("lstEstado","carga.onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listmenu, menu);
        return true;
    }

    // Reaction to the menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMenuInsert:
                Intent i = new Intent(this, TodoListOverviewPersonal.class);
                startActivity(i);
                Log.d("lstEstado","carga.onCreateOptionsMenu.insert");

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public TodoServiceImpl getTodoService() {
        return todoService;
    }

    public void setTodoService(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }
}
