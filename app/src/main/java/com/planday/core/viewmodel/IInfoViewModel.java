package com.planday.core.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

public interface IInfoViewModel {
    /**
     * Observable validation of card
     */
    ObservableInt idCardError();

    /**
     * Observable validation of email
     */
    ObservableInt emailError();

    /**
     * Observable validation of submit
     */
    ObservableBoolean canSubmit();

    /**
     * set new id card
     */
    void setIdCard(String newIdCard);

    /**
     * Set new email
     */
    void setEmail(String newEmail);

    /**
     * Command submit
     */
    rx.Observable<Boolean> submit();
}
