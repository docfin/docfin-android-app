package com.jellsoft.mobile.docfin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.ValidationErrorMessages;

public class AddNewDependantActivity extends BaseDocfinActivity {

    private ValidationErrorMessages invalidUserInputErrors = new ValidationErrorMessages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dependant);
    }


    public void addNewPatient(View view) {

    }
}
