package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.DoctorProfileAndCalendar;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.service.MockDoctorSearchService;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowSelectedDoctorCalendarAndProfile extends BaseDocfinActivity {

    private DoctorCard doctorCard;
    private DoctorProfileAndCalendar profileAndCalendar;

    RecyclerView mRecyclerView;
    AppointmentCalendarRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_doctor_calendar_profile);

        ImageView backLabel = (ImageView) findViewById(R.id.toolbar_back_image);
        backLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if ("Profile".equals(s)) {
                    setProfile();
                }
            }
        });


        Intent intent = getIntent();

        this.doctorCard = (DoctorCard) intent.getSerializableExtra(IntentConstants.BOOK_APPOINTMENT_DOCTOR_CARD);
        this.profileAndCalendar = new MockDoctorSearchService().getDoctorProfileAndCalendar(new Date(), this.doctorCard.getDocId());
        this.setDocHeader(this.doctorCard);

        findViewById(R.id.docIsFavoriteStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doctorCard.isFavorite()) {
                    ((ImageView) findViewById(R.id.docIsFavoriteStatus)).setImageResource(R.drawable.ic_action_heart);
                } else {
                    ((ImageView) findViewById(R.id.docIsFavoriteStatus)).setImageResource(R.drawable.ic_action_heart_outline);
                }
                doctorCard.toggleFavoriteStatus();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.availableDatesRecycleView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AppointmentCalendarRecyclerViewAdapter(this.profileAndCalendar.getCalendar());
        mRecyclerView.setAdapter(mAdapter);

        ArrayList<DoctorCard> doctorCards = new ArrayList<>();
        doctorCards.add(doctorCard);
        this.addToolbarMapEventListener(doctorCards);
    }

    private void setProfile() {
        ((TextView) findViewById(R.id.cv)).setText(this.profileAndCalendar.getProfile().cv);
        ((TextView) findViewById(R.id.education)).setText(this.profileAndCalendar.getProfile().education);
        ((TextView) findViewById(R.id.certifications)).setText(this.profileAndCalendar.getProfile().certifications);
        ((TextView) findViewById(R.id.languages)).setText(this.profileAndCalendar.getProfile().languages);
    }

    protected class AppointmentCalendarRecyclerView extends RecyclerView.ViewHolder {

        private CardView calendarCard;
        private DoctorProfileAndCalendar.Day day;

        public AppointmentCalendarRecyclerView(View cardItemView) {
            super(cardItemView);
            this.calendarCard = (CardView) cardItemView;
        }

        public void bindDay(final DoctorProfileAndCalendar.Day day) {
            this.day = day;
            ((TextView) calendarCard.findViewById(R.id.appointmentDate)).setText(this.day.date.toString());
            FlowLayout ll = (FlowLayout) this.calendarCard.findViewById(R.id.timeSlotsLayout);
            ll.removeAllViewsInLayout();
            for (int i = 0; i < this.day.getSlots().size(); i++) {
                Button myButton = new Button(getApplicationContext());
                final DoctorProfileAndCalendar.Day.Slot appointmentTimeSlot = this.day.getSlots().get(i);
                myButton.setText(appointmentTimeSlot.toString());
                myButton.setBackground(getDrawable(R.drawable.button_layout_rounded));
                myButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                myButton.setTextColor(Color.WHITE);
                myButton.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), NewAppointmentActivity.class);
                        intent.putExtra(IntentConstants.BOOK_APPOINTMENT_DOCTOR_CARD, doctorCard);
                        intent.putExtra(IntentConstants.BOOK_APPOINTMENT_TIME, appointmentTimeSlot);
                        startActivity(intent);
                    }
                });
                FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(5, 5, 5, 5);
                ll.addView(myButton, lp);
            }
        }

    }

    protected class AppointmentCalendarRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentCalendarRecyclerView> {

        private List<DoctorProfileAndCalendar.Day> data;

        public AppointmentCalendarRecyclerViewAdapter(List<DoctorProfileAndCalendar.Day> data) {
            this.data = data;
        }

        @Override
        public AppointmentCalendarRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.schedule_card, parent, false);
            return new AppointmentCalendarRecyclerView(view);
        }

        @Override
        public void onBindViewHolder(AppointmentCalendarRecyclerView holder, int position) {
            holder.bindDay(this.data.get(position));
        }

        @Override
        public int getItemCount() {
            return this.data == null ? 0 : this.data.size();
        }
    }

}
