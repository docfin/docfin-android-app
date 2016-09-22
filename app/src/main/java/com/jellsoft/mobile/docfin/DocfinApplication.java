package com.jellsoft.mobile.docfin;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by atulanand on 9/21/16.
 */
public class DocfinApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
