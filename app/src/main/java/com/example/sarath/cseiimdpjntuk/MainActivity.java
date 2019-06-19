package com.example.sarath.cseiimdpjntuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Button but;
    String roll_selected,password_user;
    EditText pass;
    LocalDatabase ld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.button);
        pass = (EditText)findViewById(R.id.password);
        spinner = (Spinner)findViewById(R.id.spinner_rollno_login);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll_selected = spinner.getSelectedItem().toString();
                password_user = pass.getText().toString();
                if(roll_selected.equals("please select your roll number"))
                {
                    Toast.makeText(MainActivity.this, "please select roll number", Toast.LENGTH_SHORT).show();
                }
                else {
                    ld = new LocalDatabase(spinner.getSelectedItem().toString());
                    if(roll_selected.equals(password_user)) {
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "entered password is incorect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
