package com.planday.mvvm.dependency.module;

import com.planday.mvvm.dependency.scope.ViewScope;
import com.planday.core.api.IPlacesApi;
import com.planday.utils.RetrofitUtils;
import com.planday.core.viewmodel.IPlacesViewModel;
import com.planday.biz.viewmodel.PlacesViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class PlacesModule {
    @Provides
    @ViewScope
    public IPlacesApi providePlacesApi() {
        return RetrofitUtils.create(IPlacesApi.class, "https://maps.googleapis.com/maps/api/place/");
    }

    @Provides
    @ViewScope
    public IPlacesViewModel providePlacesViewModel(IPlacesApi placesApi) {
        return new PlacesViewModel(placesApi);
    }
}