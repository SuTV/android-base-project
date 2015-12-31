package com.planday.biz.model;

import com.planday.core.model.ITestClass;

/**
 * Created by Su on 12/29/2015.
 */
public class TestClass implements ITestClass {
    public int plusOne(int a) {
        return  ++a;
    }
}
