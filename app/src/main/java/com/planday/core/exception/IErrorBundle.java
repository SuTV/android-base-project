package com.planday.core.exception;

/**
 * Created by Su on 12/29/2015.
 */

/**
 * Interface to represent a wrapper around an {@link java.lang.Exception} to manage errors.
 */
public interface IErrorBundle {
    Exception getException();

    String getErrorMessage();
}
