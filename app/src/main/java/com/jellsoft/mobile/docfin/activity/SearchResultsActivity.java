package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.transform.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultsActivity extends BaseDocfinActivity {

    RecyclerView mRecyclerView;
    DoctorCardRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();

        List<DoctorCard> searchResults = (List<DoctorCard>) intent.getSerializableExtra(IntentConstants.SEARCH_RESULTS);

        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSearchResultsList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter (see also next example)
        mAdapter = new DoctorCardRecyclerViewAdapter(searchResults);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected class DoctorCardRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private CardView doctorCardView;
        private DoctorCard doctorCard;

        public DoctorCardRecyclerViewHolder(View cardItemView)
        {
            super(cardItemView);
            this.doctorCardView = (CardView) cardItemView;

        }

        public void bindDoctorCard(DoctorCard doctorCard)
        {
            this.doctorCard = doctorCard;
            Picasso.with(getApplicationContext())
                    .load(doctorCard.getImageURL())
                    .placeholder(R.mipmap.ic_doc_placeholder)
                    .error(R.mipmap.ic_doc_placeholder)
                    .transform(new CircularTransformation())
                    .into((ImageView) this.doctorCardView.findViewById(R.id.docImage));
            ((TextView)doctorCardView.findViewById(R.id.docNameAndTitle)).setText(this.doctorCard.getNameAndTitle());
            ((TextView)doctorCardView.findViewById(R.id.docSpeciality)).setText(this.doctorCard.getSpeciality());
            ((TextView)doctorCardView.findViewById(R.id.docAddrLine1)).setText(this.doctorCard.getAddress1());
            ((TextView)doctorCardView.findViewById(R.id.docAddrLine2)).setText(this.doctorCard.getAddress2());
            if(doctorCard.isFavorite())
            {
                ((ImageView)doctorCardView.findViewById(R.id.docIsFavoriteStatus)).setImageDrawable(getDrawable(R.drawable.ic_action_heart));
            }
            else
            {
                ((ImageView)doctorCardView.findViewById(R.id.docIsFavoriteStatus)).setImageDrawable(getDrawable(R.drawable.ic_action_heart_outline));
            }
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.docIsFavoriteStatus)
            {
                //toggle doc favorite status
                if(this.doctorCard.isFavorite())
                {

                    ((ImageView)doctorCardView.findViewById(R.id.docIsFavoriteStatus)).setImageDrawable(getDrawable(R.drawable.ic_action_heart_outline));
                }
                else
                {
                    ((ImageView)doctorCardView.findViewById(R.id.docIsFavoriteStatus)).setImageDrawable(getDrawable(R.drawable.ic_action_heart));
                }
                this.doctorCard.toggleFavoriteStatus();
            }
            //TODO handle book appointment button
        }
    }

    protected class DoctorCardRecyclerViewAdapter extends RecyclerView.Adapter<DoctorCardRecyclerViewHolder>
    {
        private List<DoctorCard> data;

        public DoctorCardRecyclerViewAdapter(List<DoctorCard> data)
        {
            this.data = data;
        }

        @Override
        public DoctorCardRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.doctor_card, parent, false);
            return new DoctorCardRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DoctorCardRecyclerViewHolder holder, int position) {
            holder.bindDoctorCard(this.data.get(position));
        }

        @Override
        public int getItemCount() {
            return this.data == null ? 0 : this.data.size();
        }
    }
}
