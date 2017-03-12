package com.jellsoft.mobile.docfin;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.jellsoft.mobile.docfin.model.ValidationErrorMessages;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by atulanand on 3/12/17.
 */
public class Validator {

    private final Context context;

    private Set<ValidationEntry> userInputs = new HashSet<>();

    private ValidationErrorMessages invalidUserInputErrors = new ValidationErrorMessages();

    private Validator(Context context) {
        this.context = context;
    }

    public static Validator withContext(Context context) {
        return new Validator(context);
    }

    public Validator add(EditText input, String errorMsg) {
        this.userInputs.add(new ValidationEntry(input, errorMsg));
        return this;
    }

    public Validator add(EditText input, String errorMsg, Validation validation) {
        this.userInputs.add(new ValidationEntry(input, errorMsg, validation));
        return this;
    }

    public boolean hasNoErrors()
    {
        return this.invalidUserInputErrors.hasNoErrors();
    }

    public void validate() {
        this.clearErrorMessages();
        for (ValidationEntry entry : this.userInputs) {
            this.validateEditText(entry.input, entry.errorMsg, entry.validation);
        }
    }

    public void clearErrorMessages() {
        this.invalidUserInputErrors.reset();
        for (ValidationEntry entry : this.userInputs) {
            entry.input.setError(null);
        }
    }

    private void validateEditText(EditText editText, String msg, Validation validation) {
        if (validation.isInvalid(editText)) {
            editText.setError(msg);
            this.invalidUserInputErrors.addErrorMsg(editText.getId(), msg);
        }
    }


    private static class ValidationEntry
    {
        private final EditText input;
        private final String errorMsg;
        private final Validation validation;

        public ValidationEntry(EditText input, String errorMsg, Validation validation) {
            this.input = input;
            this.errorMsg = errorMsg;
            this.validation = validation;
        }

        public ValidationEntry(EditText input, String errorMsg) {
            this(input, errorMsg, IsEmptyValidation.instance);
        }

    }

    private static interface Validation {
        boolean isInvalid(EditText editText);
    }

    public static class IsEmptyValidation implements Validation {
        public static IsEmptyValidation instance = new IsEmptyValidation();

        private IsEmptyValidation() {
        }

        @Override
        public boolean isInvalid(EditText editText) {
            return TextUtils.isEmpty(editText.getText().toString());
        }
    }


    public static class EmailValidation implements Validation {
        public static EmailValidation instance = new EmailValidation();

        private EmailValidation() {
        }

        @Override
        public boolean isInvalid(EditText editText) {
            String input = editText.getText().toString();
            if (TextUtils.isEmpty(input)) {
                return true;
            } else {
                return !android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
            }
        }
    }
}
