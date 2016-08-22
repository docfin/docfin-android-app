package com.jellsoft.mobile.docfin.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.activity.SelectInsuranceProviderActivity;
import com.jellsoft.mobile.docfin.model.Insurance;

/**
 * Created by atulanand on 8/6/16.
 */
public class SelectInsuranceProviderFragment extends BaseSelectInsuranceFragment {

    private RecyclerView insuranceProviderRecyclerView;
    private InsuranceRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.select_insurance_fragment, container, false);
        insuranceProviderRecyclerView = (RecyclerView) v.findViewById(R.id.selectInsuranceProviderRV);
        insuranceProviderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.updateInsuranceProvidersUI();

        ImageView closeLabel = (ImageView) v.findViewById(R.id.closeSelectInsurance);
        closeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return v;
    }

    private void updateInsuranceProvidersUI() {
        adapter = new InsuranceRecyclerViewAdapter(Insurance.providers);
        insuranceProviderRecyclerView.setAdapter(adapter);
    }

    protected void onInsurancePlanSelected(String plan) {
        ((SelectInsuranceProviderActivity) getActivity()).startSelectPlanActivity(plan);
    }

}
