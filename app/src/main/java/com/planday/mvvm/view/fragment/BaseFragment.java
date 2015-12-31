package com.planday.mvvm.view.fragment;

import android.support.v4.app.Fragment;

import rx.subjects.BehaviorSubject;

public abstract class BaseFragment extends Fragment {
    private final BehaviorSubject<BaseFragment> preDestroy = BehaviorSubject.create();

    protected BehaviorSubject<BaseFragment> preDestroy() {
        return preDestroy;
    }

    @Override
    public void onDestroyView() {
        preDestroy.onNext(this);
        super.onDestroyView();
    }
}
