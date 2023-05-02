package com.api.sportyShoes.error;

public class ShoesException extends RuntimeException {

  private static final long serialVersionUID = 1338128726286682484L;

  public ShoesException() {
    super();
  }

  public ShoesException(String message) {
    super(message);
  }

}
