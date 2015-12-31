package com.planday.mvvm;

import android.app.Application;
import android.content.Context;

import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;
import com.planday.mvvm.dependency.component.AppComponent;
import com.planday.mvvm.dependency.component.DaggerAppComponent;
import com.planday.mvvm.dependency.module.AppModule;
import com.planday.biz.logging.CrashReportingTree;
import com.planday.biz.service.HipChatNotifyService;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;
import org.acra.*;
import org.acra.annotation.*;
import org.acra.sender.HttpSender;

// config report content to send to loggly
@ReportsCrashes(formUri = "http://api.loggly.com/planday?token=132324273",
        reportType = HttpSender.Type.JSON,
        httpMethod = HttpSender.Method.PUT,
        mode = ReportingInteractionMode.SILENT)

public class MyApplication extends Application {
    private static Context mContext;
    private RefWatcher mRefWatcher;
    protected AppComponent mAppComponent;
    protected ComponentBuilder mComponentBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // The following line triggers the initialization of ACRA
        ACRA.init(this);

        // install memory leak detection library (debug and release mode)
        mRefWatcher = installLeakCanary();

        if (BuildConfig.DEBUG) {
            // plant Timber for Debug mode
            Timber.plant(new Timber.DebugTree());
        } else {
            // plant Timber for product mode
            Timber.plant(new CrashReportingTree()); // or LogglyTree : all log types except ERROR

            // init ANRWatchdog : when the app NR, a crash will be raised and ACRA will catch it
            new ANRWatchDog(7000).setANRListener(new ANRWatchDog.ANRListener() {
                @Override
                public void onAppNotResponding(ANRError error) {
                    // Handle the error.
                    ACRA.getErrorReporter().handleException(error);
                }
            }).start();
        }

        // Create app component
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();

        // Create component builder
        mComponentBuilder = new ComponentBuilder(mAppComponent);

        // config realm
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public AppComponent component() {
        return mAppComponent;
    }

    public ComponentBuilder builder() {
        return mComponentBuilder;
    }

    public static Context context() {
        return mContext;
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    protected RefWatcher installLeakCanary() {
        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
                .instanceField("com.example.ExampleClass", "exampleField")
                .build();

        return LeakCanary.install(this, HipChatNotifyService.class, excludedRefs);
    }
}