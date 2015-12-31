package com.planday.mvvm.dependency.module;

import com.google.gson.Gson;
import com.planday.biz.repository.UserRepository;
import com.planday.core.repository.IUserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    public IUserRepository provideUserRepository() {
        return new UserRepository();
    }
}
