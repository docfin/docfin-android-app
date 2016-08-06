package com.jellsoft.mobile.docfin;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class SelectInsuranceProviderActivity extends SingleFragmentActivity {

    public static final String PROVIDER = "provider";

    @Override
    protected Fragment createFragment() {
        return new SelectInsuranceProviderFragment();
    }

    public void startSelectPlanActivity(int providerId)
    {
        Intent intent = new Intent(getApplicationContext(), SelectInsurancePlanActivity.class);
        intent.putExtra(PROVIDER, providerId);
        startActivityForResult(intent, 200);
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 200){

            String insurance = data.getStringExtra("insurance");
        }

    }
}
