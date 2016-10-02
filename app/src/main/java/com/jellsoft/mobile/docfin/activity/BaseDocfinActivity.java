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
import android.widget.ImageView;
import android.widget.Toast;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.realm.User;
import com.jellsoft.mobile.docfin.model.realm.UserSession;

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

    protected User registerUserDetails(String email, final String firstName, final String lastName, final String insuranceProvider, final String insurancePlan) {

        RealmResults<User> users = this.findExistingUsers(email);
        if (users.size() == 1) {
            final User user = users.get(0);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setInsuranceProvider(insuranceProvider);
                        user.setInsurancePlan(insurancePlan);
                        user.setRegistered(true);
                    } catch (Exception e) {
                        handleReamlException(e, user);
                        throw e;
                    }
                }
            });
            return user;
        }
        return null;
    }

    @NonNull
    protected User createNewUser(String email) {
        CopyToRealmTransaction<User> transaction = new CopyToRealmTransaction<>(new User(email));
        realm.executeTransaction(transaction);
        return transaction.getObj();
    }

    protected RealmResults<User> findExistingUsers(String email) {
        return realm.where(User.class).equalTo("email", email).findAll();
    }

    protected UserSession createNewSession(final User user) {
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
                handleReamlException(e, this.obj);
                this.obj = null;
                throw e;
            }
        }


        public T getObj() {
            return obj;
        }
    }

    private void handleReamlException(Exception e, RealmObject obj) {
        Log.e("ReamlTransaction", "Error saving " + obj, e);
        Toast.makeText(getApplicationContext(), "Oops.. Something went wrong. Try again.", Toast.LENGTH_SHORT);
    }

}

