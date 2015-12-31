package com.planday.mvvm.dependency.module;

import com.google.gson.Gson;
import com.planday.biz.repository.UserRepository;
import com.planday.core.repository.IUserRepository;
import com.planday.mvvm.MyApplication;
import com.planday.mvvm.dependency.scope.ViewScope;
import com.planday.core.service.IInfoService;
import com.planday.biz.service.InfoService;
import com.planday.core.viewmodel.IInfoViewModel;
import com.planday.biz.viewmodel.InfoViewModel;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class InfoModule {
    @Provides
    @ViewScope
    public IInfoService provideInfoService(Gson gson, IUserRepository repository) {
        return new InfoService(gson, repository);
    }

    @Provides
    @ViewScope
    public IInfoViewModel provideInfoViewModel(IInfoService infoService) {
        return new InfoViewModel(infoService);
    }
}
