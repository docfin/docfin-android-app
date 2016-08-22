package com.jellsoft.mobile.docfin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.activity.SelectInsurancePlanActivity;
import com.jellsoft.mobile.docfin.model.Insurance;
import com.jellsoft.mobile.docfin.model.IntentConstants;

/**
 * Created by atulanand on 8/6/16.
 */
public class SelectInsurancePlanFragment extends BaseSelectInsuranceFragment {

    private RecyclerView insuranceProviderRecyclerView;
    private InsuranceRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Intent intent = getActivity().getIntent();
        String provider = intent.getStringExtra(IntentConstants.INSURANCE_PROVIDER);

        View v = inflater.inflate(R.layout.select_insurance_plan_fragment, container, false);
        insuranceProviderRecyclerView = (RecyclerView) v.findViewById(R.id.selectInsurancePlanRV);
        insuranceProviderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.updateInsuranceProvidersUI(provider);

        ImageView backLabel = (ImageView) v.findViewById(R.id.backToInsuranceProviders);
        backLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SelectInsurancePlanActivity) getActivity()).backToInsuranceProviders();
            }
        });

        return v;
    }

    private void updateInsuranceProvidersUI(String provider) {
        adapter = new InsuranceRecyclerViewAdapter(Insurance.provider(provider).plans);
        insuranceProviderRecyclerView.setAdapter(adapter);
    }

    protected void onInsurancePlanSelected(String plan) {
        ((SelectInsurancePlanActivity) getActivity()).insurancePlanSelected(plan);
    }
}
