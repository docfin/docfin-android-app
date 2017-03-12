package com.jellsoft.mobile.docfin.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.Insurance;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.User;
import com.jellsoft.mobile.docfin.model.realm.RealmUser;
import com.jellsoft.mobile.docfin.model.realm.UserSession;
import com.jellsoft.mobile.docfin.transform.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by atulanand on 8/17/16.
 */
public abstract class BaseDocfinActivity extends AppCompatActivity {

    Realm realm;

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void startDoctorSearchActivity() {
        Intent intent = new Intent(getApplicationContext(), DoctorSearchActivity.class);
        startActivity(intent);
    }

    protected void startAddNewDependantActivity() {
        Intent intent = new Intent(getApplicationContext(), AddNewDependantActivity.class);
        startActivityForResult(intent, IntentConstants.ADD_NEW_DEPENDANT_AC);
    }

    protected void setDocHeader(DoctorCard doctorCard) {
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

        ((TextView) findViewById(R.id.docNameAndTitle)).setText(doctorCard.getNameAndTitle());
        ((TextView) findViewById(R.id.docSpeciality)).setText(doctorCard.getSpeciality());
        ((TextView) findViewById(R.id.docAddrLine)).setText(doctorCard.getAddress1() + ", " + doctorCard.getAddress2());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    protected RealmUser registerUserDetails(final User user) {

        RealmResults<RealmUser> users = this.findExistingUsers(user.getEmail());
        if (users.size() == 1) {
            final RealmUser realmUser = users.get(0);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        realmUser.setFirstName(user.getFirstName());
                        realmUser.setLastName(user.getLastName());
                        realmUser.setDob(user.getDateOfBirth());
                        realmUser.setInsuranceProvider(user.getInsuranceProvider());
                        realmUser.setDentalInsuranceProvider(user.getDentalInsuranceProvider());
                        realmUser.setVisionInsuranceProvider(user.getVisionInsuranceProvider());
                        realmUser.setInsurancePlan(user.getInsurancePlan());
                        realmUser.setDentalInsurancePlan(user.getDentalInsurancePlan());
                        realmUser.setVisionInsurancePlan(user.getVisionInsurancePlan());
                        realmUser.setRegistered(true);
                    } catch (Exception e) {
                        handleRealmException(e, realmUser);
                        throw e;
                    }
                }
            });
            return realmUser;
        }
        return null;
    }

    @NonNull
    protected RealmUser createNewUser(String email) {
        CopyToRealmTransaction<RealmUser> transaction = new CopyToRealmTransaction<>(new RealmUser(email));
        realm.executeTransaction(transaction);
        return transaction.getObj();
    }

    protected RealmResults<RealmUser> findExistingUsers(String email) {
        return realm.where(RealmUser.class).equalTo("email", email).findAll();
    }

    protected UserSession userSession() {
        RealmResults<UserSession> results = this.realm.where(UserSession.class).findAll();
        if (results.size() == 1) {
            return results.get(0);
        }
        return null;
    }

    protected UserSession createNewSession(final RealmUser user) {
        CopyToRealmTransaction<UserSession> transaction = new CopyToRealmTransaction<>(new UserSession(user, new Date()));
        realm.executeTransaction(transaction);
        return transaction.getObj();
    }

    protected void removeUserSession() {
        final RealmResults<UserSession> sessions = realm.where(UserSession.class).findAll();
        if (sessions.size() > 0) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(UserSession.class);
                }
            });
        }
    }

    protected void requestAccessLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, IntentConstants.REQUEST_PERMISSION_LOCATION);
        }
    }

    public class CopyToRealmTransaction<T extends RealmObject> implements Realm.Transaction {
        T obj;

        public CopyToRealmTransaction(T obj) {
            this.obj = obj;
        }

        @Override
        public void execute(Realm realm) {
            try {
                this.obj = realm.copyToRealm(this.obj);
            } catch (Exception e) {
                handleRealmException(e, this.obj);
                this.obj = null;
                throw e;
            }
        }


        public T getObj() {
            return obj;
        }
    }

    private void handleRealmException(Exception e, RealmObject obj) {
        Log.e("ReamlTransaction", "Error saving " + obj, e);
        Toast.makeText(getApplicationContext(), "Oops.. Something went wrong. Try again.", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.SELECT_INSURANCE_PROVIDER) {
            if (resultCode == IntentConstants.COMPLETED_WITH_RESULT) {
                String providerName = data.getStringExtra(IntentConstants.INSURANCE_PROVIDER);
                String plan = data.getStringExtra(IntentConstants.INSURANCE_PLAN);
                Insurance.Provider provider = Insurance.provider(providerName);
                String insuranceText = provider.name + ", " + plan;
                ((EditText) findViewById(R.id.insuranceId)).setText(insuranceText);
            }
        }
        if (requestCode == IntentConstants.ADD_NEW_DEPENDANT_AC) {
            if (resultCode == IntentConstants.COMPLETED_WITH_RESULT) {

            }
        }
    }

}

