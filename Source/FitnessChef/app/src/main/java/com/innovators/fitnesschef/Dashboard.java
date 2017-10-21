package com.innovators.fitnesschef;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView email;
    TextView ht;
    TextView wt;
    TextView gen;
    TextView ag;
    TextView cal;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);

            email= (TextView) findViewById(R.id.email);
            ht= (TextView) findViewById(R.id.height);
            wt= (TextView) findViewById(R.id.weight);
            gen= (TextView) findViewById(R.id.gender);
            ag= (TextView) findViewById(R.id.age);
            cal=(TextView)findViewById(R.id.calorie);

            SharedPreferences sp = getSharedPreferences("Key", Activity.MODE_PRIVATE);
            String name = sp.getString("fullname", "");
            email.setText(name);
            String height = sp.getString("height", "");
            ht.setText(height);
            String weight = sp.getString("weight", "");
            wt.setText(weight);
            String gender = sp.getString("gender", "");
            gen.setText(gender);
            String age = sp.getString("age", "");
            ag.setText(age);


            //Calorie Calculator
            double x=0;

            String comp="male";

            if (gender.equals(comp)){

                x=((10)*(Integer.parseInt(weight))+(6.25)*(Integer.parseInt(height))-(5)*(Integer.parseInt(age))+(5));
                String caloriee = String.valueOf(x);
                cal.setText(caloriee);
            }else{


                x=((10)*(Integer.parseInt(weight))+(6.25)*(Integer.parseInt(height))-(5)*(Integer.parseInt(age))-(161));
                String caloriee = String.valueOf(x);
                cal.setText(caloriee);
            }



        }
}
