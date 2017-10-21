package com.innovators.fitnesschef;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView em;
    TextView ht;
    TextView wt;
    TextView gen;
    TextView ag;
    String name;
    String height1;
    String weight1;
    String gender;
    String age1;
    SharedPreferences sp;
    String gm;
    Double x ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle extras = getIntent().getExtras();
        gm=extras.getString("email");

        em = (TextView)findViewById(R.id.email);
        ht = (TextView) findViewById(R.id.height);
        wt = (TextView) findViewById(R.id.weight);
        gen = (TextView) findViewById(R.id.gender);
        ag = (TextView) findViewById(R.id.age);

        sp = getSharedPreferences(gm, Activity.MODE_PRIVATE);
        name = sp.getString("fullname", "");
        em.setText(name);

        height1 = sp.getString("height", "");
        ht.setText(height1);

        weight1 = sp.getString("weight", "");
        wt.setText(weight1);

        gender = sp.getString("gender", "");
        gen.setText(gender);

        age1 = sp.getString("age", "");
        ag.setText(age1);

    }


    public void calorie(){

        sp = getSharedPreferences(gm,Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        //Calorie Calculator


        String comp = "male";

        if (gender.equals(comp)) {


            x = ((10) * (Integer.parseInt(weight1)) + (6.25) * (Integer.parseInt(height1)) - (5) * (Integer.parseInt(age1)) + (5));
            editor.putInt("estcal",x.intValue());
            editor.commit();
        } else {
            x = ((10) * (Integer.parseInt(weight1)) + (6.25) * (Integer.parseInt(height1)) - (5) * (Integer.parseInt(age1)) - (161));
            editor.putInt("estcal",x.intValue());
            editor.commit();
        }

    }


    public void htlink(View view){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_ht, null);
        final EditText ht1 = (EditText) mView.findViewById(R.id.alert_ht);

        Button mdone = (Button) mView.findViewById(R.id.btnDone);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ht1.getText().toString().isEmpty()){
                    Toast.makeText(ProfileActivity.this,
                            "Successfully Edited",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    sp = getSharedPreferences(gm, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor edt = sp.edit();
                    edt.putString("height",ht1.getText().toString());
                    edt.commit();
                    height1 = sp.getString("height", "");
                    Toast.makeText(ProfileActivity.this,
                            height1,
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();




                    ht.setText(ht1.getText().toString());
                    calorie();

                }
                else{
                    Toast.makeText(ProfileActivity.this,
                            "Please enter Height",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    public void wtclick(View view){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_wt, null);
        final EditText wt1 = (EditText) mView.findViewById(R.id.alert_wt);

        Button mdone = (Button) mView.findViewById(R.id.btnDone_wt);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!wt1.getText().toString().isEmpty()){
                    Toast.makeText(ProfileActivity.this,
                            "Successfully Edited",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    sp = getSharedPreferences(gm, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor edt = sp.edit();
                    edt.putString("weight",wt1.getText().toString());
                    edt.commit();
                    weight1 = sp.getString("weight", "");
                    Toast.makeText(ProfileActivity.this,
                            weight1,
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();




                    wt.setText(wt1.getText().toString());
                    calorie();

                }
                else{
                    Toast.makeText(ProfileActivity.this,
                            "Please enter Weight",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    public void ageclick(View view){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_age, null);
        final EditText ag1 = (EditText) mView.findViewById(R.id.alert_age);

        Button mdone = (Button) mView.findViewById(R.id.btnDone_age);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ag1.getText().toString().isEmpty()){
                    Toast.makeText(ProfileActivity.this,
                            "Successfully Edited",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    sp = getSharedPreferences(gm, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor edt = sp.edit();
                    edt.putString("age",ag1.getText().toString());
                    edt.commit();
                    age1 = sp.getString("age", "");
                    Toast.makeText(ProfileActivity.this,
                            age1,
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();




                    ag.setText(ag1.getText().toString());
                    calorie();

                }
                else{
                    Toast.makeText(ProfileActivity.this,
                            "Please enter Age",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
