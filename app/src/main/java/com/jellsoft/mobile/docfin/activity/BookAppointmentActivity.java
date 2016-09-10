package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.DoctorProfileAndCalendar;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.service.MockDoctorSearchService;
import com.jellsoft.mobile.docfin.transform.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class BookAppointmentActivity extends BaseDocfinActivity {

    private DoctorCard doctorCard;
    private DoctorProfileAndCalendar profileAndCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        TabHost host = (TabHost) findViewById(R.id.docDetailsTabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Calendar");
        spec.setContent(R.id.scheduleTabView);
        spec.setIndicator("Calendar");
        host.addTab(spec);

        spec = host.newTabSpec("Profile");
        spec.setContent(R.id.doctorProfileTabView);
        spec.setIndicator("Profile");
        host.addTab(spec);

        Intent intent = getIntent();

        this.doctorCard = (DoctorCard) intent.getSerializableExtra(IntentConstants.BOOK_APPOINTMENT_DOCTOR_CARD);
        this.profileAndCalendar = new MockDoctorSearchService().getDoctorProfileAndCalendar(new Date(), this.doctorCard.getDocId());
        this.setDocHeader();


    }

    private void setDocHeader() {
        if (doctorCard.isFavorite()) {
            ((ImageView) findViewById(R.id.docIsFavoriteStatus)).setImageResource(R.drawable.ic_action_heart);
        } else {
            ((ImageView) findViewById(R.id.docIsFavoriteStatus)).setImageResource(R.drawable.ic_action_heart_outline);
        }

        Picasso.with(getApplicationContext())
                .load(doctorCard.getImageURL())
                .placeholder(R.drawable.doctor_placeholder)
                .error(R.drawable.doctor_placeholder)
                .transform(new CircularTransformation())
                .into((ImageView) findViewById(R.id.docImage));

        ((TextView) findViewById(R.id.docNameAndTitle)).setText(this.doctorCard.getNameAndTitle());
        ((TextView) findViewById(R.id.docSpeciality)).setText(this.doctorCard.getSpeciality());
        ((TextView) findViewById(R.id.docAddrLine)).setText(this.doctorCard.getAddress1() + ", " + this.doctorCard.getAddress2());
    }

    protected class AppointmentCalendarRecyclerView extends RecyclerView.ViewHolder {

        private CardView doctorCardView;
        private DoctorCard doctorCard;

        public AppointmentCalendarRecyclerView(View cardItemView) {
            super(cardItemView);
            this.doctorCardView = (CardView) cardItemView;
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
            if (this.doctorCard.isFavorite()) {
                ((ImageView) view).setImageResource(R.drawable.ic_action_heart_outline);
            } else {
                ((ImageView) view).setImageResource(R.drawable.ic_action_heart);
            }
            this.doctorCard.toggleFavoriteStatus();
        }

        public void bookAnAppointment(View view) {
            Intent intent = new Intent(getApplicationContext(), BookAppointmentActivity.class);
            startActivity(intent);
        }
    }

    protected class DoctorCardRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentCalendarRecyclerView> {
        private List<DoctorCard> data;

        public DoctorCardRecyclerViewAdapter(List<DoctorCard> data) {
            this.data = data;
        }

        @Override
        public AppointmentCalendarRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.doctor_card, parent, false);
            return new AppointmentCalendarRecyclerView(view);
        }

        @Override
        public void onBindViewHolder(AppointmentCalendarRecyclerView holder, int position) {
            holder.bindDoctorCard(this.data.get(position));
        }

        @Override
        public int getItemCount() {
            return this.data == null ? 0 : this.data.size();
        }
    }

}
