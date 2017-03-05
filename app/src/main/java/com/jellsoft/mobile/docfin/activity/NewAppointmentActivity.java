package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.DoctorProfileAndCalendar;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.realm.RealmUser;
import com.jellsoft.mobile.docfin.model.realm.UserSession;

import java.text.SimpleDateFormat;

public class NewAppointmentActivity extends BaseDocfinActivity {

    DoctorCard doctorCard;
    DoctorProfileAndCalendar.Day.Slot appointmentTime;

    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appoinment);

        ((TextView)findViewById(R.id.toolbar_sr_title)).setText("Book Appointment");

        Intent intent = getIntent();
        this.doctorCard = (DoctorCard) intent.getSerializableExtra(IntentConstants.BOOK_APPOINTMENT_DOCTOR_CARD);
        this.appointmentTime = (DoctorProfileAndCalendar.Day.Slot) intent.getSerializableExtra(IntentConstants.BOOK_APPOINTMENT_TIME);

        this.setDocHeader(this.doctorCard);

        TextView appointmentTimeView = (TextView) findViewById(R.id.appointmentTime);
        appointmentTimeView.setText(appointmentTime.toString() + " on " + sdf.format(appointmentTime.getDay().date));

        UserSession session = userSession();
        if(session != null) {
            RadioButton me = (RadioButton) findViewById(R.id.appointmentForSelf);
            RealmUser user = session.getUser();
            me.setText(user.getFirstName() + " " + user.getLastName());
        }
    }

    //TODO: implement this method...
    public void choosePatient(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.appointmentForSelf:
                if (checked)
                    break;
            case R.id.appointmentForNewPatient:
                if (checked)
                    startAddNewDependantActivity();
                    break;
        }
    }


}
