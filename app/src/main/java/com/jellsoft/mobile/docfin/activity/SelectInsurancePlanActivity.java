package com.jellsoft.mobile.docfin.activity;

import android.support.v4.app.Fragment;

import com.jellsoft.mobile.docfin.SelectInsurancePlanFragment;
import com.jellsoft.mobile.docfin.model.IntentConstants;

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
        setResult(200, getIntent());
        getIntent().putExtra(IntentConstants.INSURANCE_PLAN, planId);
        finish();
    }

    public void backToInsuranceProviders()
    {
        setResult(100, getIntent());
        finish();
    }
}
