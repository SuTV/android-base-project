package com.planday.biz.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import com.planday.biz.entity.User;
import com.planday.biz.factory.RepositoryFactory;
import com.planday.biz.repository.UserRepository;
import com.planday.core.service.IInfoService;
import com.planday.core.entity.IUser;
import com.planday.core.repository.IUserRepository;
import com.planday.biz.model.Info;
import rx.Observable;
import timber.log.Timber;

public class InfoService implements IInfoService {
    private Gson mGson;
    private IUserRepository mRepository;

    public InfoService(@NonNull Gson gson, @NonNull IUserRepository repository) {
        mGson = gson;
        mRepository = repository;
    }

    /**
     * Fake networking
     */
    public Observable<Boolean> submitInfo(String idCard, String email) {
        Info info = new Info(idCard, email);
        return Observable.create(subscriber -> {
            try {
                String json = mGson.toJson(info);

                IUser existedUser = mRepository.get(new Object[]{info.getIdCard()});
                if (existedUser == null) {
                    IUser user = new User();
                    user.setIdCard(info.getIdCard());
                    user.setEmail(info.getEmail());
                    user.setUserName(info.getIdCard());

                    mRepository.create(user);
                    Timber.d("Create successfully");
                } else {
                    Timber.d("User exists");
                }

                //Timber.d(repository.customMethod());

                Thread.sleep((json.length() % 3) * 1000);

                subscriber.onNext(true);
                subscriber.onCompleted();
            } catch (Exception exception) {
                Timber.e(exception.getMessage());
                subscriber.onError(exception);
            }
        });
    }
}