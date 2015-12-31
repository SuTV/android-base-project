package com.planday.biz.repository;

import com.planday.biz.entity.User;
import com.planday.core.entity.IUser;
import com.planday.core.repository.IUserRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Su on 12/29/2015.
 */

public class UserRepository extends BaseRepository implements IUserRepository {

    @Override
    public List<IUser> getAll() {
        return null;
    }

    @Override
    public IUser get(Object[] keys) {
        IUser result = null;

        if(keys != null && keys.length > 0) {
            String idCard = keys[0].toString();
            // Build the query looking at all users:
            RealmQuery<User> query = getRealm().where(User.class);

            // Add query conditions:
            query.equalTo("idCard", idCard);

            // Execute the query:
            result = (IUser) query.findFirst();
        }

        return result;
    }

    @Override
    public IUser create(IUser user) {
        Realm realm = getRealm();
        realm.beginTransaction();

        User userData = realm.createObject(User.class);
        userData.setUserName(user.getUserName());
        userData.setIdCard(user.getIdCard());
        userData.setEmail(user.getEmail());

        realm.commitTransaction();

//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                User userData = realm.createObject(User.class);
//                userData.setId(user.getId());
//                userData.setUserName(user.getUserName());
//                userData.setIdCard(user.getIdCard());
//                userData.setEmail(user.getEmail());
//            }
//        });

        return  userData;
    }

    @Override
    public IUser update(IUser iUser) {
        return null;
    }

    @Override
    public IUser delete(IUser iUser) {
        return null;
    }

    @Override
    public String customMethod() {
        return "CUSTOM METHOD R...";
    }


}
