package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jellsoft.mobile.docfin.SelectInsuranceProviderFragment;
import com.jellsoft.mobile.docfin.model.IntentConstants;

public class SelectInsuranceProviderActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SelectInsuranceProviderFragment();
    }

    public void startSelectPlanActivity(int providerId)
    {
        Intent intent = new Intent(getApplicationContext(), SelectInsurancePlanActivity.class);
        intent.putExtra(IntentConstants.INSURANCE_PROVIDER, providerId);
        startActivityForResult(intent, IntentConstants.COMPLETED_WITH_RESULT);
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == IntentConstants.COMPLETED_WITH_RESULT){

            int providerId = data.getIntExtra(IntentConstants.INSURANCE_PROVIDER, -1);
            int planId = data.getIntExtra(IntentConstants.INSURANCE_PLAN, -1);
            getIntent().putExtra(IntentConstants.INSURANCE_PROVIDER, providerId);
            getIntent().putExtra(IntentConstants.INSURANCE_PLAN, planId);
            setResult(IntentConstants.COMPLETED_WITH_RESULT, getIntent());

            finish();
        }
    }
}
