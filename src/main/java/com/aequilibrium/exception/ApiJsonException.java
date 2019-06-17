/*
* Copyright 2018 Builders
*************************************************************
*Nome     : ApiJsonException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package com.aequilibrium.exception;

public class ApiJsonException extends Exception {

  private static final long serialVersionUID = 5456484840178149688L;

  public ApiJsonException() {
    super();
  }

  public ApiJsonException(final String message, final Throwable cause, final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ApiJsonException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ApiJsonException(final String message) {
    super(message);
  }

  public ApiJsonException(final Throwable cause) {
    super(cause);
  }

}

