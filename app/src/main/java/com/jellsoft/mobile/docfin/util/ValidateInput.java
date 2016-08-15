package com.jellsoft.mobile.docfin.util;

import android.text.TextUtils;

/**
 * Created by atulanand on 8/14/16.
 */
public class ValidateInput {

    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
