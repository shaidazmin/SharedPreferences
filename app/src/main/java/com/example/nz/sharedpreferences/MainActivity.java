package com.example.nz.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userText, passText;
    Button saveButton, showButton,clearButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText = (EditText) findViewById(R.id.userNameEdtiText);
        passText = (EditText) findViewById(R.id.passwordEdtiText);
        saveButton = (Button) findViewById(R.id.saveButton);
        showButton = (Button) findViewById(R.id.showButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

       if(v.getId() == R.id.clearButton){
           userText.setText("");
           passText.setText("");
       }

       else if(v.getId() == R.id.saveButton){
            String username = userText.getText().toString();
            String password = passText.getText().toString();
            if(username.equals("")&& password.equals("")){
                Toast.makeText(MainActivity.this,"Please Enter User Name & Password",Toast.LENGTH_SHORT).show();
            }
            else {

                SharedPreferences sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey",username);
                editor.putString("passwordKey",password);
                editor.commit();
                Toast.makeText(MainActivity.this,"User Name & Password is saved",Toast.LENGTH_SHORT).show();

            }



        }
        else if (v.getId() == R.id.showButton){
            SharedPreferences sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKey")  && sharedPreferences.contains("passwordKey") ){

                String username = sharedPreferences.getString("usernameKey","Data is not found");
                String password = sharedPreferences.getString("passwordKey","Data is not found");
                resultTextView.setText("User Id : "+username+"\n"+"User Password : "+password);

            } else {
                Toast.makeText(MainActivity.this,"Data server Error! ", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
