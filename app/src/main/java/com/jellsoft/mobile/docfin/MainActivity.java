package com.jellsoft.mobile.docfin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(R.id.firstName);
        editText.requestFocus();
    }

    public void registerUser(View view) {
        //view is the button.
        System.out.println("Register User");
    }
}
