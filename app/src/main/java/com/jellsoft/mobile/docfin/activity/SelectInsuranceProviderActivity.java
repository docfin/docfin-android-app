package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jellsoft.mobile.docfin.fragment.SelectInsuranceProviderFragment;
import com.jellsoft.mobile.docfin.model.IntentConstants;

public class SelectInsuranceProviderActivity extends BaseInsuranceFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SelectInsuranceProviderFragment();
    }

    public void startSelectPlanActivity(String provider)
    {
        Intent intent = new Intent(getApplicationContext(), SelectInsurancePlanActivity.class);
        intent.putExtra(IntentConstants.INSURANCE_PROVIDER, provider);
        startActivityForResult(intent, IntentConstants.SELECT_INSURANCE_PLAN);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == IntentConstants.COMPLETED_WITH_RESULT){
            String providerName = data.getStringExtra(IntentConstants.INSURANCE_PROVIDER);
            String plan = data.getStringExtra(IntentConstants.INSURANCE_PLAN);
            getIntent().putExtra(IntentConstants.INSURANCE_PROVIDER, providerName);
            getIntent().putExtra(IntentConstants.INSURANCE_PLAN, plan);
            setResult(IntentConstants.COMPLETED_WITH_RESULT, getIntent());
            finish();
        }
    }
}
