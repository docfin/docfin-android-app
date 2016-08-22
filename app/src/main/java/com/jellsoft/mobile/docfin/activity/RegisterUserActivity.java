package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.Insurance;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.ValidationErrorMessages;
import com.jellsoft.mobile.docfin.util.ValidateInput;

public class RegisterUserActivity extends BaseDocfinActivity {

    private ValidationErrorMessages errorMessages = new ValidationErrorMessages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        findViewById(R.id.insuranceId).clearFocus();
        findViewById(R.id.firstName).requestFocus();
        this.closeKeyboard();
    }


    public void registerUser(View view) {
        //view is the button.
        this.closeKeyboard();
        this.errorMessages.reset();
        validateUserRegistrationFields();
        if (errorMessages.hasNoErrors())
        {
            Intent intent = new Intent(getApplicationContext(), DoctorSearchActivity.class);
            startActivity(intent);
        }
    }

    public void selectInsurance(View insuranceET) {
        this.closeKeyboard();
        Intent intent = new Intent(getApplicationContext(), SelectInsuranceProviderActivity.class);
        startActivityForResult(intent, IntentConstants.COMPLETED_WITH_RESULT);

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == IntentConstants.COMPLETED_WITH_RESULT){
            String providerName = data.getStringExtra(IntentConstants.INSURANCE_PROVIDER);
            String plan = data.getStringExtra(IntentConstants.INSURANCE_PLAN);
            Insurance.Provider provider = Insurance.provider(providerName);
            String insuranceText = provider.name + ", " + plan;
            ((EditText)findViewById(R.id.insuranceId)).setText(insuranceText);
        }

    }
    private void validateUserRegistrationFields() {
        validateEditText((EditText) findViewById(R.id.firstName));
        validateEditText((EditText) findViewById(R.id.lastName));
        EditText email = (EditText) findViewById(R.id.emailId);
        validateEditText(email);
        validateEditText((EditText) findViewById(R.id.insuranceId));
        if(!ValidateInput.isValidEmail(email.getText().toString()))
        {
            showErrorMsg(email, getString(R.string.user_email_error));
        }
    }

    private void validateEditText(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            switch (editText.getId()) {
                case R.id.firstName:
                    showErrorMsg(editText, getString(R.string.user_firstName_error));
                    break;
                case R.id.lastName:
                    showErrorMsg(editText, getString(R.string.user_lastName_error));
                    break;
                case R.id.emailId:
                    showErrorMsg(editText, getString(R.string.user_email_error));
                    break;
                case R.id.insuranceId:
                    showErrorMsg(editText, getString(R.string.user_insurance_error));
                    break;
                default:
                    break;
            }
        }

    }

    private void showErrorMsg(EditText editText, String msg)
    {
        editText.setError(msg);
        this.errorMessages.addErrorMsg(editText.getId(), msg);
    }
}
