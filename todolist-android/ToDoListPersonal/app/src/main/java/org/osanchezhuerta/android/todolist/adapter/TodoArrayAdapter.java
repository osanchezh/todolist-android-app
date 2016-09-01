package org.osanchezhuerta.android.todolist.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.osanchezhuerta.android.todolist.R;
import org.osanchezhuerta.android.todolist.vo.ToDo;

/**
 * Created by sanchezocth on 31/08/2016.
 */
public class TodoArrayAdapter extends ArrayAdapter<ToDo> {
    private final Context context;
    private final ToDo[] objects;

    public TodoArrayAdapter(Context context, ToDo[] objects) {
        super(context, R.layout.activity_todo_listview, objects);
        this.context=context;
        this.objects=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_todo_listview, parent, false);
        TextView txtSummary = (TextView) rowView.findViewById(R.id.txtLvSummary);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txtLvDescription);

        txtSummary.setText(objects[position].getSummary());
        txtDescription.setText(objects[position].getDescription());

        Log.d("T","position="+position+"=,objects[position].getId()="+objects[position].getId());


        return rowView;
    }


}
