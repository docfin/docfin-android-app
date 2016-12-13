package com.jellsoft.mobile.docfin.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.fragment.DatePickerFragment;
import com.jellsoft.mobile.docfin.model.DateFormatter;
import com.jellsoft.mobile.docfin.model.Insurance;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.User;
import com.jellsoft.mobile.docfin.model.ValidationErrorMessages;
import com.jellsoft.mobile.docfin.model.realm.RealmUser;
import com.jellsoft.mobile.docfin.service.MockUserRegistrationService;
import com.jellsoft.mobile.docfin.util.ValidateInput;

import java.util.Date;

public class RegisterUserActivity extends BaseDocfinActivity implements View.OnClickListener, OnDateSelectedListener {

    private EditText firstName;
    private EditText lastName;
    private EditText dateOfBirth;
    private EditText emailId;
    private EditText insuranceId;
    private EditText dentalInsuranceId;
    private EditText visionInsuranceId;

    DateFormatter dobDateFormatter;

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
        this.emailId = (EditText) findViewById(R.id.emailId);
        this.dateOfBirth = (EditText) findViewById(R.id.dob);
        this.dentalInsuranceId = (EditText) findViewById(R.id.dentalInsuranceId);
        this.visionInsuranceId = (EditText) findViewById(R.id.visionInsuranceId);

        this.firstName.requestFocus();

        this.firstName.setOnClickListener(this);
        this.lastName.setOnClickListener(this);
        this.emailId.setOnClickListener(this);
        this.dobDateFormatter = DateFormatter.withFormat(getString(R.string.dateFormatDOB));

        Intent intent = getIntent();

        if (intent.getSerializableExtra(IntentConstants.SIGN_IN_ACCOUNT) != null) {
            com.jellsoft.mobile.docfin.model.User userAccount = (com.jellsoft.mobile.docfin.model.User) intent.getSerializableExtra(IntentConstants.SIGN_IN_ACCOUNT);
            this.firstName.setText(userAccount.getFirstName());
            this.lastName.setText(userAccount.getLastName());
            this.emailId.setText(userAccount.getEmail());

            if(userAccount.getDateOfBirth()!= null)
                this.dateOfBirth.setText(dobDateFormatter.toString(userAccount.getDateOfBirth()));
            this.hydrateInsurance(userAccount);
            this.hydrateDentalInsurance(userAccount);
            this.hydrateVisionInsurance(userAccount);
        }
    }

    private void hydrateInsurance(User userAccount) {
        if (userAccount.getInsuranceProvider() != null && userAccount.getInsurancePlan() != null) {
            this.insuranceId.setText(userAccount.getInsuranceProvider() + ", " + userAccount.getInsurancePlan());
        }
    }

    private void hydrateDentalInsurance(User userAccount) {
        if (userAccount.getDentalInsuranceProvider() != null && userAccount.getDentalInsurancePlan() != null) {
            this.insuranceId.setText(userAccount.getDentalInsuranceProvider() + ", " + userAccount.getDentalInsurancePlan());
        }
    }

    private void hydrateVisionInsurance(User userAccount) {
        if (userAccount.getInsuranceProvider() != null && userAccount.getInsurancePlan() != null) {
            this.insuranceId.setText(userAccount.getVisionInsuranceProvider() + ", " + userAccount.getVisionInsurancePlan());
        }
    }

    public void registerUser(View view) {
        //view is the button.
        this.closeKeyboard();
        this.clearErrorMessages();
        validateUserRegistrationFields();
        if (errorMessages.hasNoErrors()) {

            User user = createUser();
            RealmUser registeredUser = registerUserDetails(user);
            //TODO register on server.
            if (registeredUser != null) {
                new MockUserRegistrationService().registerUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getInsuranceProvider(), user.getInsurancePlan());
                doOnRegistrationSuccess();
            }
        }
    }

    private User createUser() {
        User user = new User(this.emailId.getText().toString(), this.firstName.getText().toString(), this.lastName.getText().toString());

        user.setDateOfBirth(dobDateFormatter.toDate(this.dateOfBirth.getText().toString()));
        user.setInsuranceProvider(getProviderAndPlan(this.insuranceId)[0]);
        user.setInsuranceProvider(getProviderAndPlan(this.insuranceId)[1]);

        user.setDentalInsuranceProvider(getProviderAndPlan(this.dentalInsuranceId)[0]);
        user.setDentalInsurancePlan(getProviderAndPlan(this.dentalInsuranceId)[1]);

        user.setVisionInsuranceProvider(getProviderAndPlan(this.visionInsuranceId)[0]);
        user.setVisionInsurancePlan(getProviderAndPlan(this.visionInsuranceId)[1]);
        return user;
    }

    private String[] getProviderAndPlan(EditText typeOfInsurance) {
        if (typeOfInsurance.getText() != null && !typeOfInsurance.getText().toString().isEmpty()) {
            String insuranceStr = typeOfInsurance.getText().toString();
            return insuranceStr.split(",");
        } else {
            return new String[]{"Self", "Cash"};
        }
    }


    private void doOnRegistrationSuccess() {
        Toast.makeText(getApplicationContext(), "Registration successful! Happy doctor hunting", Toast.LENGTH_LONG).show();
        startDoctorSearchActivity();
    }


    private void doOnRegistrationFailed() {
        Toast.makeText(getApplicationContext(), "Yikes, something went wrong with registration. Try again", Toast.LENGTH_LONG).show();
    }

    private void clearErrorMessages() {
        this.errorMessages.reset();
        this.firstName.setError(null);
        this.lastName.setError(null);
        this.emailId.setError(null);
        this.dateOfBirth.setError(null);
        this.insuranceId.setError(null);
    }

    public void selectDOB(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(DatePickerFragment.TYPE_KEY, DatePickerFragment.DatePickerType.DOB);
        newFragment.setArguments(args);
        newFragment.show(getFragmentManager(), "DOBDatePicker");
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
        validateEditText((EditText) findViewById(R.id.dob));
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

    @Override
    public void onDateSelected(Date date) {
        EditText dateButton = (EditText) findViewById(R.id.dob);
        dateButton.setText(dobDateFormatter.toString(date));
    }
}
