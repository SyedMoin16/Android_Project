package com.innovators.fitnesschef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText pas;
    private EditText cpas;
    private EditText email;
    private EditText nam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pas = (EditText) findViewById(R.id.pass);
        cpas = (EditText) findViewById(R.id.cpass);
        email = (EditText) findViewById(R.id.Email);
        nam =    (EditText) findViewById(R.id.Name);

        pas.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        cpas.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        email.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        nam.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);


    }

    public void reg(View v)
    {

        String s=email.getText().toString();
        SharedPreferences settings = getSharedPreferences(s,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email",s);
        editor.putString("username",nam.getText().toString());
        editor.putString("password",pas.getText().toString());
        editor.commit();
        Intent redirect=new Intent(RegisterActivity.this,GoalActivity.class);
        redirect.putExtra("email",s);
        startActivity(redirect);
    }
}
