package com.jellsoft.mobile.docfin;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.insuranceId).clearFocus();
        findViewById(R.id.firstName).requestFocus();
    }

    public void registerUser(View view) {
        //view is the button.
        validateUserRegistrationFields();
    }

    private void validateUserRegistrationFields()
    {
        validateEditText((EditText)findViewById(R.id.firstName));
        validateEditText((EditText)findViewById(R.id.lastName));
        validateEditText((EditText)findViewById(R.id.emailId));
        validateEditText((EditText)findViewById(R.id.insuranceId));
    }

    private void validateEditText(EditText editText)
    {
        if(editText.getText().toString().trim().isEmpty())
        {
            switch (editText.getId())
            {
                case R.id.firstName:
                    editText.setError(getString(R.string.user_firstName_error));
                    break;
                case R.id.lastName:
                    editText.setError(getString(R.string.user_lastName_error));
                    break;
                case R.id.emailId:
                    editText.setError(getString(R.string.user_email_error));
                    break;
                case R.id.insuranceId:
                    editText.setError(getString(R.string.user_insurance_error));
                    break;
                default:
                    break;
            }
        }
    }
}
