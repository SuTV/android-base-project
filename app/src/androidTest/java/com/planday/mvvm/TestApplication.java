package com.planday.mvvm;


import com.planday.mvvm.dependency.component.AppComponent;

public class TestApplication extends MyApplication {
    public void setComponent(AppComponent appComponent) {
        this.mAppComponent = appComponent;
    }

    public void setComponentBuilder(ComponentBuilder componentBuilder) {
        this.mComponentBuilder = componentBuilder;
    }
}
