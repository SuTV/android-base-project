package com.planday.mvvm.dependency.module;

import com.planday.mvvm.dependency.scope.ViewScope;
import com.planday.core.model.ITestClass;
import com.planday.biz.model.TestClass;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Su on 12/29/2015.
 */

@Module
public class TestModule {
    @Provides
    @ViewScope
    public ITestClass provideTestClass() {
        return new TestClass();
    }
}
