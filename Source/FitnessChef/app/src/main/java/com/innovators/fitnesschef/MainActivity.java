package com.innovators.fitnesschef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginpage(View v)
    {


                Intent redirect=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(redirect);
            }



    public void in(View v)
    {
        Intent redirect=new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(redirect);
    }

}
