package com.jellsoft.mobile.docfin;

import android.support.v4.app.Fragment;

/**
 * Created by atulanand on 8/6/16.
 */
public class SelectInsurancePlanActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SelectInsurancePlanFragment();
    }

    public void insurancePlanSelected(int planId)
    {

    }

    public void backToInsuranceProviders()
    {
        setResult(100, getIntent());
        finish();
    }
}
