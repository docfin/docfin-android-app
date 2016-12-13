package com.jellsoft.mobile.docfin.model.realm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by atulanand on 9/26/16.
 */
public class UserSession extends RealmObject {

    private RealmUser user;

    @Required
    private Date loggedInTime;

    public UserSession(RealmUser user, Date loggedInTime) {
        this.user = user;
        this.loggedInTime = loggedInTime;
    }

    public UserSession() {
    }

    public RealmUser getUser() {
        return user;
    }

    public void setUser(RealmUser user) {
        this.user = user;
    }

    public Date getLoggedInTime() {
        return loggedInTime;
    }

    public void setLoggedInTime(Date loggedInTime) {
        this.loggedInTime = loggedInTime;
    }
}
