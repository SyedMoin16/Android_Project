package com.innovators.fitnesschef;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DetailsActivity extends AppCompatActivity {

    EditText fullname;
    EditText age;
    RadioButton m;
    RadioButton f;
    SharedPreferences sp;
    Double d;
 String gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        gm=extras.getString("email");
    }

         public void details(View v){

        fullname = (EditText) findViewById(R.id.fullname);
        age = (EditText) findViewById(R.id.ddmmyyyy);
        m=(RadioButton)findViewById(R.id.male);
             f=(RadioButton)findViewById(R.id.female);
      //  Button nxt = (Button) findViewById(R.id.next);



//        nxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                String userName = fullname.getText().toString();
                String person_age = age.getText().toString();

             sp = getSharedPreferences(gm, Activity.MODE_PRIVATE);
             String w=sp.getString("weight","");
             String h=sp.getString("height","");
             SharedPreferences.Editor editor = sp.edit();
             if(m.isChecked()) {
                 d = (10 * (Float.parseFloat(w)) + (6.25 * (Float.parseFloat(h))) - (5 * Float.parseFloat(person_age)) + 5);
                 editor.putString("gender",m.getText().toString());
             }
             else
             {
                 d = (10 * (Float.parseFloat(w)) + (6.25 * (Float.parseFloat(h))) - (5 * Float.parseFloat(person_age)) - 161);
                 editor.putString("gender",f.getText().toString());

             }

                editor.putString("fullname", userName);
                editor.putString("age", person_age);
             editor.putInt("estcal",d.intValue());
                editor.commit();

                Log.d("DetailsActivity",userName);
             Intent redirect = new Intent(DetailsActivity.this, home.class);
             redirect.putExtra("email",gm);
             startActivity(redirect);

            }

    }


