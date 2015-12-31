package com.planday.mvvm.view.handler;

import android.text.TextWatcher;
import android.view.View;

public interface IInfoHandler {
    TextWatcher emailWatcher();
    TextWatcher idCardWatcher();
    View.OnClickListener onSubmit();
    View.OnClickListener onInvalid();
}
