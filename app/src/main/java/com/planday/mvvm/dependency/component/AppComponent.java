package com.planday.mvvm.dependency.component;

import javax.inject.Singleton;

import com.planday.mvvm.dependency.module.AppModule;
import com.planday.mvvm.dependency.module.PlacesModule;
import com.planday.mvvm.dependency.module.InfoModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    InfoComponent plus(InfoModule infoModule);
    PlacesComponent plus(PlacesModule placesModule);
}
