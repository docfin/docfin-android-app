package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.Insurance;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.ValidationErrorMessages;
import com.jellsoft.mobile.docfin.util.ValidateInput;

public class RegisterUserActivity extends BaseDocfinActivity implements View.OnClickListener {

    private EditText firstName;
    private EditText lastName;
    private EditText emailId;
    private EditText insuranceId;

    private ValidationErrorMessages errorMessages = new ValidationErrorMessages();

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.clearErrorMessages();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        this.insuranceId = (EditText) findViewById(R.id.insuranceId);
        this.firstName = (EditText) findViewById(R.id.firstName);
        this.lastName = (EditText) findViewById(R.id.lastName);
        this.emailId = (EditText) findViewById(R.id.lastName);

        this.insuranceId.requestFocus();

        this.firstName.setOnClickListener(this);
        this.lastName.setOnClickListener(this);
        this.emailId.setOnClickListener(this);

        Intent intent = getIntent();

        GoogleSignInAccount userAccount = intent.getExtras().getParcelable(IntentConstants.SIGN_IN_ACCOUNT);

        this.firstName.setText(userAccount.getGivenName());
        this.lastName.setText(userAccount.getFamilyName());
        this.emailId.setText(userAccount.getEmail());
    }

    public void registerUser(View view) {
        //view is the button.
        this.closeKeyboard();
        this.clearErrorMessages();
        validateUserRegistrationFields();
        if (errorMessages.hasNoErrors()) {
            Intent intent = new Intent(getApplicationContext(), DoctorSearchActivity.class);
            startActivity(intent);
        }
    }

    private void clearErrorMessages() {
        this.errorMessages.reset();
        this.firstName.setError(null);
        ((EditText) findViewById(R.id.lastName)).setError(null);
        ((EditText) findViewById(R.id.emailId)).setError(null);
        ((EditText) findViewById(R.id.insuranceId)).setError(null);
    }

    public void selectInsurance(View selectInsurance) {
        this.closeKeyboard();
        ((EditText) selectInsurance).setError(null);
        Intent intent = new Intent(getApplicationContext(), SelectInsuranceProviderActivity.class);
        startActivityForResult(intent, IntentConstants.SELECT_INSURANCE_PROVIDER);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.SELECT_INSURANCE_PROVIDER) {
            if (resultCode == IntentConstants.COMPLETED_WITH_RESULT) {
                String providerName = data.getStringExtra(IntentConstants.INSURANCE_PROVIDER);
                String plan = data.getStringExtra(IntentConstants.INSURANCE_PLAN);
                Insurance.Provider provider = Insurance.provider(providerName);
                String insuranceText = provider.name + ", " + plan;
                ((EditText) findViewById(R.id.insuranceId)).setText(insuranceText);
            }
        }
    }

    private void validateUserRegistrationFields() {
        validateEditText((EditText) findViewById(R.id.firstName));
        validateEditText((EditText) findViewById(R.id.lastName));
        EditText email = (EditText) findViewById(R.id.emailId);
        validateEditText(email);
        validateEditText((EditText) findViewById(R.id.insuranceId));
        if (!ValidateInput.isValidEmail(email.getText().toString())) {
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

    private void showErrorMsg(EditText editText, String msg) {
        editText.setError(msg);
        this.errorMessages.addErrorMsg(editText.getId(), msg);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof EditText) {
            ((EditText) view).setError(null);
        }
    }
}
