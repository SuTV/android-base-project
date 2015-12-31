package com.planday.core.service;

import rx.Observable;

public interface IInfoService {
    Observable<Boolean> submitInfo(String idCard, String email);
}
