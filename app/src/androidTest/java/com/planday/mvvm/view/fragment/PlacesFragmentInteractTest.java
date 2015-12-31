package com.planday.mvvm.view.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;

import com.planday.mvvm.view.activity.TestActivity;

@SmallTest
@RunWith(JUnit4.class)
public class PlacesFragmentInteractTest {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule =
            new ActivityTestRule<>(TestActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        activityTestRule.launchActivity(new Intent());
        activityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, PlacesFragment.newInstance())
                .commit();
    }

    @Test
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void testImpl() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        activityTestRule.getActivity().getApplication()
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                    }

                    @Override
                    public void onActivityStopped(Activity activity) {
                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {
                        if (activityTestRule.getActivity() == activity) {
                            latch.countDown();
                            activityTestRule.getActivity().getApplication()
                                    .unregisterActivityLifecycleCallbacks(this);
                        }
                    }
                });
        latch.await();
    }

}
