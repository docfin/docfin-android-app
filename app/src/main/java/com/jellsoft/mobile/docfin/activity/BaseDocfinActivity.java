package com.jellsoft.mobile.docfin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.IntentConstants;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by atulanand on 8/17/16.
 */
public abstract class BaseDocfinActivity extends AppCompatActivity {

    Realm realm;


    public void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void startDoctorSearchActivity() {
        Intent intent = new Intent(getApplicationContext(), DoctorSearchActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.closeKeyboard();
    }

    protected void addToolbarBackEventListener() {
        ImageView backLabel = (ImageView) findViewById(R.id.toolbar_back_image);
        backLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void addToolbarMapEventListener(final ArrayList<DoctorCard> doctorCards) {
        ImageView mapIcon = (ImageView) findViewById(R.id.toolbar_sr_map);
        mapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra(IntentConstants.MAPS_ADDRESSES, doctorCards);
                startActivity(intent);
            }
        });
    }
}
