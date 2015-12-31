package com.planday.biz.repository;

import io.realm.Realm;

/**
 * Created by Su on 12/30/2015.
 */
public class BaseRepository {
    protected Realm getRealm() {
        return Realm.getDefaultInstance();
    }
}
