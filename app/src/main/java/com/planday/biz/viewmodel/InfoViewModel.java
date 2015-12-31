package com.planday.biz.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import com.planday.core.viewmodel.IInfoViewModel;
import com.planday.mvvm.R;
import com.planday.core.service.IInfoService;
import com.planday.utils.NumericUtils;
import com.planday.utils.StringUtils;

public class InfoViewModel extends BaseObservable implements IInfoViewModel {
    private final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final int TIME_OUT = 5;
    private final int RETRY = 3;

    // Normal property
    private String mIdCard;
    private String mEmail;
    private boolean mIdCardValid;
    private boolean mEmailValid;

    // Observable property
    private ObservableBoolean mCanSubmit = new ObservableBoolean(false);
    private ObservableInt mEmailError = new ObservableInt(R.string.empty);
    private ObservableInt mIdCardError = new ObservableInt(R.string.empty);

    // Dependency
    private IInfoService mInfoService;

    public InfoViewModel(@NonNull IInfoService infoService) {
        mInfoService = infoService;
    }

    @Override
    public ObservableInt idCardError() {
        return mIdCardError;
    }

    @Override
    public ObservableInt emailError() {
        return mEmailError;
    }

    @Override
    public ObservableBoolean canSubmit() {
        return mCanSubmit;
    }

    @Override
    public void setIdCard(String newIdCard) {
        if (StringUtils.isEmpty(newIdCard)) return;
        mIdCard = newIdCard;
        mIdCardValid = newIdCard.length() == 9 && NumericUtils.isNumeric(newIdCard);
        mIdCardError.set(mIdCardValid ? R.string.empty : R.string.error_id_card);
        updateCanSubmit();
    }

    @Override
    public void setEmail(String newEmail) {
        if (StringUtils.isEmpty(newEmail)) return;
        mEmail = newEmail;
        mEmailValid = newEmail.matches(EMAIL_REGEX);
        mEmailError.set(mEmailValid ? R.string.empty : R.string.error_email);
        updateCanSubmit();
    }

    @Override
    public rx.Observable<Boolean> submit() {
        return mInfoService.submitInfo(mIdCard, mEmail)
                .timeout(TIME_OUT, TimeUnit.SECONDS)
                .retry(RETRY);
    }

    private void updateCanSubmit() {
        mCanSubmit.set(mIdCardValid && mEmailValid);
    }
}
