package com.planday.mvvm.utils;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import com.planday.mvvm.TestApplication;

public class ApplicationUtils {
    public static TestApplication application() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        return (TestApplication) instrumentation.getTargetContext().getApplicationContext();
    }
}
