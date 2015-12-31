package com.planday.mvvm;

import com.planday.mvvm.dependency.component.AppComponent;
import com.planday.mvvm.dependency.component.PlacesComponent;
import com.planday.mvvm.dependency.component.InfoComponent;
import com.planday.mvvm.dependency.module.PlacesModule;
import com.planday.mvvm.dependency.module.InfoModule;

/**
 * Use to build subcomponent
 */
public class ComponentBuilder {
    private AppComponent appComponent;

    public ComponentBuilder(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public PlacesComponent placesComponent() {
        return appComponent.plus(new PlacesModule());
    }

    public InfoComponent infoComponent() {
        return appComponent.plus(new InfoModule());
    }
}
