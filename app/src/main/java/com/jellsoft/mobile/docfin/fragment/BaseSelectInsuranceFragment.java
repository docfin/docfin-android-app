package com.jellsoft.mobile.docfin.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;

import java.util.List;

/**
 * Created by atulanand on 8/21/16.
 */
public abstract class BaseSelectInsuranceFragment extends Fragment {

    protected abstract void onInsurancePlanSelected(String selectedText);

    protected class InsuranceRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public InsuranceRecyclerViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.list_item_textView);
            this.textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onInsurancePlanSelected(((TextView) view).getText().toString());
        }
    }

    protected class InsuranceRecyclerViewAdapter<T> extends RecyclerView.Adapter<InsuranceRecyclerViewHolder> {

        private List<T> data;

        public InsuranceRecyclerViewAdapter(List<T> data) {
            this.data = data;
        }

        @Override
        public InsuranceRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.textview_line, parent, false);
            return new InsuranceRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(InsuranceRecyclerViewHolder holder, int position) {
            T provider = data.get(position);
            holder.textView.setText(provider.toString());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}
