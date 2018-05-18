package com.imdadareeph.appserver.exception;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
public class ExceptionResponse {

  private String errorCode;
  private String errorMessage;

  public ExceptionResponse() {}

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
