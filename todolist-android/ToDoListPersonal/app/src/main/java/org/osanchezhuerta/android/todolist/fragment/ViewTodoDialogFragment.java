package org.osanchezhuerta.android.todolist.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osanchezhuerta.android.todolist.R;
import org.osanchezhuerta.android.todolist.TodoListPersonal;
import org.osanchezhuerta.android.todolist.service.TodoServiceImpl;
import org.osanchezhuerta.android.todolist.vo.ToDo;

import java.text.ParseException;

/**
 * Created by sanchezocth on 31/08/2016.
 */
public class ViewTodoDialogFragment extends DialogFragment {
    public static final String ARG_ITEM_ID = "emp_dialog_fragment";
    final String LOG_TAG = "ViewTodoDialog";


    private TodoServiceImpl todoService;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        final ToDo todo = bundle.getParcelable("selectedToDo");
        todoService = new TodoServiceImpl(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View customDialogView = inflater.inflate(R.layout.fragment_view_todo,null);

        TextView etxtSelectedId=(TextView)customDialogView.findViewById(R.id.txtSelectedId);
        etxtSelectedId.setText(todo.getId()+"");
        EditText etxtSummary=(EditText)customDialogView.findViewById(R.id.txtSelectedSummary);
        etxtSummary.setText(todo.getSummary());
        EditText etxtDescripcion=(EditText)customDialogView.findViewById(R.id.txtSelectedDescription);
        etxtDescripcion.setText(todo.getDescription());

        builder.setView(customDialogView);
        builder.setTitle("Visualizar Todo");
        builder.setCancelable(false);
        /*
        builder.setPositiveButton(R.string.update,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        */
        final Button btnCancelar = (Button) customDialogView.findViewById(R.id.button_reset);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("test","test123");
                dismiss();
            }
        });

        final Button btnActualizar = (Button) customDialogView.findViewById(R.id.button_add);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(guardar(customDialogView,todo)) {
                    TodoListPersonal activity = (TodoListPersonal) getActivity();
                    activity.fillData();
                    Log.d("test", "test123");
                    dismiss();
                }
            }
        });
        return builder.create();
    }

    public boolean guardar(View customDialogView,ToDo todoSelected){
        Log.d("guardar=","1");
        boolean valido=true;
        EditText etxtSummary=(EditText)customDialogView.findViewById(R.id.txtSelectedSummary);
        EditText etxtDescripcion=(EditText)customDialogView.findViewById(R.id.txtSelectedDescription);
        String ssummary = etxtSummary.getText().toString();
        String sdescription = etxtDescripcion.getText().toString();
        if(TextUtils.isEmpty(ssummary)){
            Toast.makeText(getActivity(),
                    "Debe registrar summary.",
                    Toast.LENGTH_SHORT).show();
            valido=false;
        }else if(TextUtils.isEmpty(sdescription)){
            Toast.makeText(getActivity(),
                    "Debe registrar description.",
                    Toast.LENGTH_SHORT).show();
            valido=false;
        }else {
            todoSelected.setDescription(sdescription);
            todoSelected.setSummary(ssummary);
            Log.d("ssummary=",ssummary);
            Log.d("sdescription=",sdescription);
            todoService.update(todoSelected);
        }
        return valido;
    }

    public void onClickActualizar(View v) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        dismiss();
    }
    public void onClickCancelar(View v) {
        Log.d(LOG_TAG, "Dialog 2: " + ((Button) v).getText());
        dismiss();
    }


    public TodoServiceImpl getTodoService() {
        return todoService;
    }

    public void setTodoService(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_todo, null);

        return view;
    }*/
}
