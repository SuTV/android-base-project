package com.planday.mvvm.view.activity;

import android.os.Bundle;

import com.planday.mvvm.R;
import com.planday.mvvm.view.fragment.PlacesFragment;

/**
 * User this for testing purpose only
 */
public class PlacesActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, PlacesFragment.newInstance())
                .commit();
    }
}
