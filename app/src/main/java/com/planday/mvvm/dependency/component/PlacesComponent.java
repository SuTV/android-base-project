package com.planday.mvvm.dependency.component;

import com.planday.mvvm.dependency.module.PlacesModule;
import com.planday.mvvm.dependency.scope.ViewScope;
import com.planday.mvvm.view.fragment.PlacesFragment;
import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {PlacesModule.class})
public interface PlacesComponent {
    void inject(PlacesFragment placesFragment);
}
