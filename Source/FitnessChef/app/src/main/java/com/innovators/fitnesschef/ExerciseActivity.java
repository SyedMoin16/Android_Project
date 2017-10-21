package com.innovators.fitnesschef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    private ArrayAdapter mAdapter;
    public ListView l;
    ArrayList<String> myStringArray1;
    String x;
    String q;
    String g;
    int v;
    static int k=0;
    String datecontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent = getIntent();
        g = intent.getStringExtra("email");
        datecontent=intent.getStringExtra("date");
        l = (ListView) findViewById(R.id.list);
        myStringArray1 = new ArrayList<String>();
        SharedPreferences settings = getSharedPreferences(g+"exercise"+datecontent, 0);
        //     SharedPreferences.Editor editor = settings.edit();
        for(int j=1;j<=settings.getInt("listsize",0);j++)
        {
            String r=  settings.getString("exerciselist"+j,"");
            myStringArray1.add(r);
        }
        k=settings.getInt("listsize",0);
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myStringArray1);
        l.setAdapter(mAdapter);
    }
    public void additem(View v)
    {
        EditText t=(EditText) findViewById(R.id.editText3);
        x=t.getText().toString();
        quantity();

    }
    public void quantity() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseActivity.this);
        builder.setTitle("Calories Burnt!");
        TextView tv=new TextView(ExerciseActivity.this);
        tv.setText("Quantity:");
        final EditText input = new EditText(ExerciseActivity.this);
        input.setText("1");
        builder.setView(tv);
        builder.setView(input);
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        q=input.getText().toString();
                        if(q.isEmpty())
                        {
                            q="0";
                        }
                        dialog.cancel();
                        dis(x);
                    }

                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
                dis(x);
            }
        });
        builder.show();

    }
    private void dis(String x)
    {
        k=k+1;
        myStringArray1.add(x.toUpperCase()+",           "+"CALORIES: "+q);

        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myStringArray1);
        l.setAdapter(mAdapter);
        SharedPreferences settings = getSharedPreferences(g+"exercise"+datecontent, 0);
        v=settings.getInt("value",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("exerciselist"+k,x.toUpperCase()+",           "+"CALORIES: "+q);
        editor.putInt("listsize",k);
        editor.putInt("value",(Integer.parseInt(q.toString())+v));
        editor.commit();
    }
}
