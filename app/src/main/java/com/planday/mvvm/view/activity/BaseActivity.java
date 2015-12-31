package com.planday.mvvm.view.activity;

import android.support.v7.app.AppCompatActivity;

import rx.subjects.BehaviorSubject;

public abstract class BaseActivity extends AppCompatActivity {

    private final BehaviorSubject<BaseActivity> preDestroy = BehaviorSubject.create();

    protected BehaviorSubject<BaseActivity> preDestroy() {
        return preDestroy;
    }

    @Override
    protected void onDestroy() {
        preDestroy.onNext(this);
        super.onDestroy();
    }
}
