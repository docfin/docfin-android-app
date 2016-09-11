package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.MapAddress;
import com.jellsoft.mobile.docfin.transform.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new DoctorCardRecyclerViewAdapter(searchResults);
        mRecyclerView.setAdapter(mAdapter);

        addToolbarBackEventListener();
        addToolbarMapEventListener(getMapAddresses(searchResults));
    }

    private ArrayList<MapAddress> getMapAddresses(List<DoctorCard> doctorCards)
    {
        ArrayList addresses = new ArrayList();
        for(DoctorCard dc: doctorCards)
        {
            addresses.add(new MapAddress(dc.fullAddress(), dc.getNameAndTitle(), dc.getImageURL()));
        }
        return addresses;
    }


    protected class DoctorCardRecyclerViewHolder extends RecyclerView.ViewHolder {

        private CardView doctorCardView;
        private DoctorCard doctorCard;

        public DoctorCardRecyclerViewHolder(View cardItemView) {
            super(cardItemView);
            this.doctorCardView = (CardView) cardItemView;
            this.doctorCardView.findViewById(R.id.docIsFavoriteStatus).setOnClickListener(new ToggleFavoriteStatusListener(this));
            this.doctorCardView.findViewById(R.id.docAppointmentButton).setOnClickListener(new BookAppointmentListener(this));
        }

        public void bindDoctorCard(DoctorCard doctorCard) {
            this.doctorCard = doctorCard;
            Picasso.with(getApplicationContext())
                    .load(doctorCard.getImageURL())
                    .placeholder(R.drawable.doctor_placeholder)
                    .error(R.drawable.doctor_placeholder)
                    .transform(new CircularTransformation())
                    .into((ImageView) this.doctorCardView.findViewById(R.id.docImage));
            ((TextView) doctorCardView.findViewById(R.id.docNameAndTitle)).setText(this.doctorCard.getNameAndTitle());
            ((TextView) doctorCardView.findViewById(R.id.docSpeciality)).setText(this.doctorCard.getSpeciality());
            ((TextView) doctorCardView.findViewById(R.id.docAddrLine1)).setText(this.doctorCard.getAddress1());
            ((TextView) doctorCardView.findViewById(R.id.docAddrLine2)).setText(this.doctorCard.getAddress2());
            if (doctorCard.isFavorite()) {
                ((ImageView) doctorCardView.findViewById(R.id.docIsFavoriteStatus)).setImageDrawable(getDrawable(R.drawable.ic_action_heart));
            } else {
                ((ImageView) doctorCardView.findViewById(R.id.docIsFavoriteStatus)).setImageDrawable(getDrawable(R.drawable.ic_action_heart_outline));
            }
        }

        public void toggleFavoriteStatus(View view) {
            //TODO: remove this? user can only heart from bookappointment tab
            if (this.doctorCard.isFavorite()) {
                ((ImageView) view).setImageResource(R.drawable.ic_action_heart_outline);
            } else {
                ((ImageView) view).setImageResource(R.drawable.ic_action_heart);
            }
            this.doctorCard.toggleFavoriteStatus();
        }

        public void bookAnAppointment(View view)
        {
            Intent intent = new Intent(getApplicationContext(), BookAppointmentActivity.class);
            intent.putExtra(IntentConstants.BOOK_APPOINTMENT_DOCTOR_CARD, this.doctorCard);
            startActivity(intent);
        }
    }

    protected class DoctorCardRecyclerViewAdapter extends RecyclerView.Adapter<DoctorCardRecyclerViewHolder> {
        private List<DoctorCard> data;

        public DoctorCardRecyclerViewAdapter(List<DoctorCard> data) {
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

    private class ToggleFavoriteStatusListener implements View.OnClickListener {

        private DoctorCardRecyclerViewHolder viewHolder;

        public ToggleFavoriteStatusListener(DoctorCardRecyclerViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            //toggle doc favorite status
            Log.d("ToggleFavoriteStatus", "Toggle status ");
            this.viewHolder.toggleFavoriteStatus(view);
        }
    }

    private class BookAppointmentListener implements View.OnClickListener {

        private DoctorCardRecyclerViewHolder viewHolder;

        public BookAppointmentListener(DoctorCardRecyclerViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            this.viewHolder.bookAnAppointment(view);
        }
    }
}
