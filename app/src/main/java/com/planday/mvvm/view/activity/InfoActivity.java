package com.planday.mvvm.view.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import com.planday.mvvm.MyApplication;
import com.planday.mvvm.R;
import com.planday.mvvm.databinding.ActivityInfoBinding;
import com.planday.core.model.ITestClass;
import com.planday.utils.ui.TextWatcherAdapter;
import com.planday.utils.ui.UiUtils;
import com.planday.mvvm.view.handler.IInfoHandler;
import com.planday.core.viewmodel.IInfoViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class InfoActivity extends BaseActivity implements IInfoHandler {
    private ProgressDialog mProgressDialog;
    private ActivityInfoBinding binding;

    @Inject
    IInfoViewModel mViewModel;

    @Inject
    ITestClass mTestClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Setup DI
        ((MyApplication) getApplication())
                .builder()
                .infoComponent()
                .inject(this);

        // bind viewmodel
        bindViewModel();

        // setup other views
        setUpView();

        Timber.d("----------------------" + mTestClass.plusOne(100) + "----------------------");
    }

    private void bindViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info);
        binding.setViewModel(mViewModel);
        binding.setHandler(this);
    }

    private void setUpView() {
        // Toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Progress dialog
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setCancelable(false);
    }

    @Override
    public TextWatcher emailWatcher() {
        return new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setEmail(s.toString());
            }
        };
    }

    @Override
    public TextWatcher idCardWatcher() {
        return new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setIdCard(s.toString());
            }
        };
    }

    @Override
    public View.OnClickListener onSubmit() {
        return v -> mViewModel.submit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .takeUntil(preDestroy())
                .doOnSubscribe(mProgressDialog::show)
                .doOnTerminate(mProgressDialog::hide)
                .subscribe(success -> {
                    UiUtils.showDialog(getString(R.string.success), this);
                }, throwable -> {
                    UiUtils.showDialog(getString(R.string.error), this);
                });
    }

    @Override
    public View.OnClickListener onInvalid() {
        return v -> {
            // Do something
            Timber.d("Invalid");
        };
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
