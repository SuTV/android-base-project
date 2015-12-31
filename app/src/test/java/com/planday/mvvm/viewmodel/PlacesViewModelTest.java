package com.planday.mvvm.viewmodel;

import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import com.planday.biz.viewmodel.PlacesViewModel;
import com.planday.core.api.IPlacesApi;
import com.planday.biz.model.GoogleSearchResult;
import com.planday.biz.model.Place;
import com.planday.utils.TestDataUtils;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@MediumTest
@RunWith(JUnit4.class)
public class PlacesViewModelTest {
    private PlacesViewModel placesViewModel;
    private IPlacesApi placesApi;
    private TestSubscriber<Boolean> testSubscriber;

    @Before
    public void setUpViewModel() {
        placesApi = Mockito.mock(IPlacesApi.class);
        placesViewModel = new PlacesViewModel(placesApi);
        testSubscriber = TestSubscriber.create();
        when(placesApi.placesResult()).thenReturn(testDataObservable());
    }

    @Test
    public void fetchAllPlacesSuccess() {
        placesViewModel.fetchAllPlaces().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(true));
    }

    @Test
    public void fetchAllPlaces() {
        placesViewModel.fetchAllPlaces().subscribe();
        assertEquals(placesViewModel.getCurrentPlaces().size(), 10);
    }

    @Test
    public void filterAll() {
        assertEquals(getAndFilterWith("all").size(), 10);
        getAndFilterWith("cafe");
        assertEquals(getAndFilterWith("all").size(), 10);
    }

    @Test
    public void filterFood() {
        assertEquals(getAndFilterWith("food").size(), 4);
        getAndFilterWith("cafe");
        assertEquals(getAndFilterWith("food").size(), 4);
    }

    @Test
    public void filterCafe() {
        assertEquals(getAndFilterWith("cafe").size(), 5);
    }

    @Test
    public void filterStore() {
        assertEquals(getAndFilterWith("store").size(), 4);
    }

    @Test
    public void filterRestaurant() {
        assertEquals(getAndFilterWith("restaurant").size(), 3);
        getAndFilterWith("cafe");
        assertEquals(getAndFilterWith("restaurant").size(), 3);
    }

    @Test
    public void filterTheater() {
        assertEquals(getAndFilterWith("theater").size(), 3);
    }

    private List<Place> getAndFilterWith(String type) {
        placesViewModel.fetchAllPlaces().subscribe();
        placesViewModel.filterPlacesByType(type);
        return placesViewModel.getCurrentPlaces();
    }

    private Observable<GoogleSearchResult> testDataObservable() {
        return Observable.just(TestDataUtils.nearByData());
    }
}