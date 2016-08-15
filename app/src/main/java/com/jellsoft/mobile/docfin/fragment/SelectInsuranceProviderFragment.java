package com.jellsoft.mobile.docfin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.activity.SelectInsuranceProviderActivity;
import com.jellsoft.mobile.docfin.model.Insurance;

/**
 * Created by atulanand on 8/6/16.
 */
public class SelectInsuranceProviderFragment extends Fragment
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.select_insurance_fragment, container, false);
        this.populateInsuranceProviders(v);

        TextView closeLabel = (TextView)v.findViewById(R.id.closeSelectInsurance);
        closeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        ListView insuranceProviderList = (ListView) v.findViewById(R.id.insuranceProviders);
        insuranceProviderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ((SelectInsuranceProviderActivity)getActivity()).startSelectPlanActivity(position);
            }
        });

        return v;
    }

    private void populateInsuranceProviders(View v) {
        ArrayAdapter<Insurance.Provider> insuranceProviders = new ArrayAdapter(v.getContext(),
                R.layout.textviewonly, Insurance.providers);
        ListView listView = (ListView) v.findViewById(R.id.insuranceProviders);
        listView.setAdapter(insuranceProviders);
    }

}
