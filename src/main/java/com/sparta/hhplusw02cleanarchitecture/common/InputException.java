package com.sparta.hhplusw02cleanarchitecture.common;

/**
 * 유효하지 않은 input 데이터 관련 익셉션 클래스
 */
public class InputException extends RuntimeException{
  public InputException(String message) {
    super(message);
  }
}
