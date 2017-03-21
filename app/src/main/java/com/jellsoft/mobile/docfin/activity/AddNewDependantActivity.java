package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.Validator;

public class AddNewDependantActivity extends BaseDocfinActivity {

    private Validator validator;
    private EditText firstName;
    private EditText lastName;
    private EditText dobDay;
    private EditText dobYear;
    private Spinner dobMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dependant);
        this.firstName = (EditText) findViewById(R.id.firstName);
        this.lastName = (EditText) findViewById(R.id.lastName);
        this.dobMonth = (Spinner) findViewById(R.id.dobMonth);
        this.dobDay = (EditText) findViewById(R.id.dobDay);
        this.dobYear = (EditText) findViewById(R.id.dobYear);
        this.setUpDOBMonths(this.dobMonth);
        this.setUpValidator();
    }

    private void setUpValidator()
    {
        this.validator = Validator.withContext(this);
        this.validator
                .add(this.firstName, this.getString(R.string.user_firstName_error))
                .add(this.lastName, this.getString(R.string.user_lastName_error))
                .add(this.dobDay, this.getString(R.string.user_dob_error))
                .add(this.dobYear, this.getString(R.string.user_dob_error))
        ;
    }

    public void addNewPatient(View view) {

    }
}
