package com.planday.mvvm.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.planday.mvvm.R;
import com.planday.mvvm.databinding.ActivityMainBinding;
import com.planday.mvvm.view.handler.IMainHandler;

public class MainActivity extends BaseActivity implements IMainHandler {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View.OnClickListener infoSample() {
        return v -> startActivity(new Intent(this, InfoActivity.class));
    }

    @Override
    public View.OnClickListener placeSample() {
        return v -> startActivity(new Intent(this, PlacesActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
