package com.innovators.fitnesschef;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class GoalActivity extends AppCompatActivity {

    private RadioButton r1;
    private  RadioButton r2;
    private RadioButton r3;
    private EditText h;
    private EditText w;
    private TextView t;
    String g;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        r1=(RadioButton) findViewById(R.id.lose);
        r2=(RadioButton) findViewById(R.id.maintain);
        r3=(RadioButton) findViewById(R.id.gain);
        h=(EditText) findViewById(R.id.ht);
        w=(EditText) findViewById(R.id.wt);
        t=(TextView) findViewById(R.id.goal1);

        h.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        w.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        Bundle extras = getIntent().getExtras();
        g=extras.getString("email");
    }

    public void details(View v)
    {
        String s=h.getText().toString();
        String u=w.getText().toString();
        if(r1.isChecked()|r2.isChecked()|r3.isChecked())
        {
            if(!s.isEmpty()&!u.isEmpty())
            {
                Log.d("GoalActivity",s);
                //shared preferences
                SharedPreferences sp = getSharedPreferences(g,0);
                SharedPreferences.Editor edt = sp.edit();
                edt.putString("height",s);
                edt.putString("weight",u);
                edt.commit();
                if(r1.isChecked()) {
                    Intent redirect = new Intent(GoalActivity.this, targetgoalActivity.class);
                    redirect.putExtra("email", g);
                    startActivity(redirect);
                }
                else if(r2.isChecked())
                {
                    Intent redirect = new Intent(GoalActivity.this, DetailsActivity.class);
                    redirect.putExtra("email", g);
                    startActivity(redirect);
                }
                else
                {
                    Intent redirect = new Intent(GoalActivity.this, targetgoalActivity.class);
                    redirect.putExtra("email", g);
                    startActivity(redirect);
                }
            }
            else{
                if(s.isEmpty()&u.isEmpty())
                {
                    h.setError("This field is required");
                    w.setError("This filed is required");
                }
                else if(s.isEmpty()){
                    h.setError("This filed is required");
                }
                else
                {
                    w.setError("This filed is required");
                }
            }
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(GoalActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Your goal is not set");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }




    }



}
