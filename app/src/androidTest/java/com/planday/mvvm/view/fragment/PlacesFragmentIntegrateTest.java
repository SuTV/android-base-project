package com.planday.mvvm.view.fragment;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import com.planday.mvvm.ComponentBuilder;
import com.planday.mvvm.R;
import com.planday.mvvm.dependency.component.AppComponent;
import com.planday.mvvm.dependency.component.PlacesComponent;
import com.planday.mvvm.dependency.module.PlacesModule;
import com.planday.core.api.IPlacesApi;
import com.planday.mvvm.utils.ApplicationUtils;
import com.planday.utils.TestDataUtils;
import com.planday.mvvm.view.activity.TestActivity;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.planday.mvvm.utils.MatcherEx.hasItemCount;
import static org.mockito.Mockito.when;

@MediumTest
@RunWith(JUnit4.class)
public class PlacesFragmentIntegrateTest {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule =
            new ActivityTestRule<>(TestActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        PlacesModule mockModule = new PlacesModule() {
            @Override
            public IPlacesApi providePlacesApi() {
                IPlacesApi placesApi = Mockito.mock(IPlacesApi.class);
                when(placesApi.placesResult())
                        .thenReturn(Observable.just(TestDataUtils.nearByData()));
                return placesApi;
            }
        };

        // Setup test component
        AppComponent component = ApplicationUtils.application().component();
        ApplicationUtils.application().setComponentBuilder(new ComponentBuilder(component) {
            @Override
            public PlacesComponent placesComponent() {
                return component.plus(mockModule);
            }
        });

        activityTestRule.launchActivity(new Intent());
        activityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, PlacesFragment.newInstance())
                .commit();

        // TODO: fix this problem
        // Sometime espresso cannot find the view with id: "R.id.action_sort"
        // Maybe the view has not been completed rendered
        // Currently put it to sleep 1000ms
        Thread.sleep(1000);
    }

    @Test
    public void testAllPlacesOnLayout() throws Exception {
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(10)));
    }

    @Test
    public void clickFood() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Food")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(4)));
    }

    @Test
    public void clickCafe() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Cafe")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(5)));
    }

    @Test
    public void clickStore() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Store")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(4)));
    }

    @Test
    public void clickTheater() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Theater")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(3)));
    }

    @Test
    public void clickRestaurant() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Restaurant")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(3)));
    }

    @Test
    public void clickAll() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("All")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(10)));
    }

    @Test
    public void clickRestaurantAfterCafe() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Cafe")).perform(click());
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Restaurant")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(3)));
    }

    @Test
    public void clickAllAfterCafe() throws Exception {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("Cafe")).perform(click());
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText("All")).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(hasItemCount(10)));
    }
}
