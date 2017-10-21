package com.innovators.fitnesschef;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  {
    private EditText email;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
     getSupportActionBar().setLogo(R.drawable.ic_arrow_back_white_24dp);
      getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_login);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent redirect = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(redirect);
                        return true;
                    }

                });

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void valid(View v) {
        email = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        email.setError(null);
        pass.setError(null);
        String x = email.getText().toString();
        String y = pass.getText().toString();
        if (x.isEmpty() & y.isEmpty()) {
            email.setError("Email is required");
            pass.setError("Passowrd is required");
        } else if (x.isEmpty()) {
            email.setError("Email is required");
        } else if (y.isEmpty()) {
            pass.setError("Password is required");
        } else {
            SharedPreferences sp = getSharedPreferences(x, Activity.MODE_PRIVATE);
            if (x.equals(sp.getString("email","")) & y.equals(sp.getString("password",""))) {
                Intent redirect = new Intent(LoginActivity.this, home.class);
                redirect.putExtra("email",x);
                startActivity(redirect);
            } else {
                email.setError("Email or Password Mismatch");
                pass.setError("Email or Password Mismatch");
            }
        }




    }





}
