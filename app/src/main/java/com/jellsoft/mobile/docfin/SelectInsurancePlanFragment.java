package com.jellsoft.mobile.docfin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.Insurance;

/**
 * Created by atulanand on 8/6/16.
 */
public class SelectInsurancePlanFragment extends Fragment
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final Intent intent = getActivity().getIntent();
        int providerId = intent.getIntExtra(IntentConstants.INSURANCE_PROVIDER, 0);

        View v = inflater.inflate(R.layout.select_insurance_plan_fragment, container, false);
        this.populateInsurancePlans(v, Insurance.providers.get(providerId));

        TextView backLabel = (TextView)v.findViewById(R.id.backToInsuranceProviders);
        backLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SelectInsurancePlanActivity)getActivity()).backToInsuranceProviders();
            }
        });

        ListView insurancePlans = (ListView) v.findViewById(R.id.insurancePlans);
        insurancePlans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ((SelectInsurancePlanActivity)getActivity()).insurancePlanSelected(position);
            }
        });

        return v;
    }

    private void populateInsurancePlans(View v, Insurance.Provider provider) {
        ArrayAdapter<Insurance.Plan> plans = new ArrayAdapter(v.getContext(),
                R.layout.textviewonly, provider.plans);
        ListView listView = (ListView) v.findViewById(R.id.insurancePlans);
        listView.setAdapter(plans);
    }

}
