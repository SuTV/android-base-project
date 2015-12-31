package com.planday.mvvm.dependency.component;

import com.planday.mvvm.dependency.module.InfoModule;
import com.planday.mvvm.dependency.module.TestModule;
import com.planday.mvvm.dependency.scope.ViewScope;
import com.planday.mvvm.view.activity.InfoActivity;
import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {InfoModule.class, TestModule.class})
public interface InfoComponent {
    void inject(InfoActivity infoActivity);
}
