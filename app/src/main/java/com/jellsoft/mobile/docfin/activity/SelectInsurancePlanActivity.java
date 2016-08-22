package com.jellsoft.mobile.docfin.activity;

import android.support.v4.app.Fragment;

import com.jellsoft.mobile.docfin.fragment.SelectInsurancePlanFragment;
import com.jellsoft.mobile.docfin.model.IntentConstants;

/**
 * Created by atulanand on 8/6/16.
 */
public class SelectInsurancePlanActivity extends BaseInsuranceFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new SelectInsurancePlanFragment();
    }

    public void insurancePlanSelected(String plan)
    {
        setResult(200, getIntent());
        getIntent().putExtra(IntentConstants.INSURANCE_PLAN, plan);
        finish();
    }

    public void backToInsuranceProviders()
    {
        setResult(100, getIntent());
        finish();
    }
}
