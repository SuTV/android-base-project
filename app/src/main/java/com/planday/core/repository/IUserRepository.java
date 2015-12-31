package com.planday.core.repository;

import com.planday.core.entity.IUser;

import java.util.List;

import rx.Observable;

/**
 * Created by Su on 12/29/2015.
 */
public interface IUserRepository extends IRepository<IUser> {
    String customMethod();
}
