package com.jellsoft.mobile.docfin.popupmenu;

import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.activity.DoctorSearchActivity;
import com.jellsoft.mobile.docfin.activity.RegisterUserActivity;
import com.jellsoft.mobile.docfin.activity.SignInActivity;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.model.realm.RealmUser;
import com.jellsoft.mobile.docfin.model.realm.UserSession;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by atulanand on 8/11/16.
 */
public class PopupMenuManager implements View.OnClickListener {


    public PopupMenuManager() {
    }

    @Override
    public void onClick(final View view) {

        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mi_appointments:
                        //todo
                        return true;

                    case R.id.mi_search:
                        startDoctorSearchActivity(view);
                        return true;
                    case R.id.mi_settings:
                        startUserRegistrationActivity(view);
                        return true;
                    case R.id.mi_signout:
                        signOut(view);
                        return true;

                    default:
                        return true;
                }
            }
        });
        popupMenu.inflate(R.menu.toolbar_menu);
        popupMenu.show();
    }

    private void signOut(View view) {
        Intent signOutUser = new Intent(view.getContext(), SignInActivity.class);
        signOutUser.putExtra(IntentConstants.SIGN_OUT_USER, true);
        view.getContext().startActivity(signOutUser);
    }

    private void startDoctorSearchActivity(View view) {
        Intent doctorSearch = new Intent(view.getContext(), DoctorSearchActivity.class);
        view.getContext().startActivity(doctorSearch);
    }

    private void startUserRegistrationActivity(View view) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserSession> sessions = realm.where(UserSession.class).findAll();
        if(sessions.size() == 1)
        {
            UserSession session = sessions.get(0);
            Intent userRegistration = new Intent(view.getContext(), RegisterUserActivity.class);
            RealmUser user = session.getUser();
            userRegistration.putExtra(IntentConstants.SIGN_IN_ACCOUNT, new com.jellsoft.mobile.docfin.model.User(user));
            view.getContext().startActivity(userRegistration);
        }else
        {
            Toast.makeText(view.getContext(), "Oops something went wrong..", Toast.LENGTH_SHORT);
            signOut(view);
        }
    }

}
