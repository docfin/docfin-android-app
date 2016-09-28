package com.jellsoft.mobile.docfin;

import android.app.Application;

import com.jellsoft.mobile.docfin.model.realm.Migration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by atulanand on 9/21/16.
 */
public class DocfinApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1).deleteRealmIfMigrationNeeded().migration(new Migration()).build();
        Realm.setDefaultConfiguration(config);
    }
}
