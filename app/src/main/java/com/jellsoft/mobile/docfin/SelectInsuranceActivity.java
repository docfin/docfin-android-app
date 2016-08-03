package com.jellsoft.mobile.docfin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jellsoft.mobile.docfin.model.Insurance;

public class SelectInsuranceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_insurance);
        this.populateInsuranceProviders();
    }

    private void populateInsuranceProviders() {
        ArrayAdapter<Insurance.Provider> insuranceProviders = new ArrayAdapter<>(this,
                R.layout.textviewonly, Insurance.providers);
        ListView listView = (ListView) findViewById(R.id.insuranceProviders);
        listView.setAdapter(insuranceProviders);
    }

    public void exitInsurancePicker(View closeInsuranceLabel)
    {
        finish();
    }
}
