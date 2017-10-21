package com.innovators.fitnesschef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class targetgoalActivity extends AppCompatActivity {
String g;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targetgoal);
        Intent intent = getIntent();
        g = intent.getStringExtra("email");
        e=(EditText)findViewById(R.id.editText4);
    }

    public void fuck(View v)
    {
        SharedPreferences sp = getSharedPreferences(g,0);
        SharedPreferences.Editor edt = sp.edit();
        edt.putString("weight",e.getText().toString());
        Intent in=new Intent(targetgoalActivity.this,DetailsActivity.class);
        in.putExtra("email",g);
        startActivity(in);
    }
}
