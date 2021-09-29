/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.exception;

public class IdMustNotBeNullException extends RuntimeException{

  public IdMustNotBeNullException() {
    super("Id must not be null.");
  }

  public IdMustNotBeNullException(String message) {
    super(message);
  }
}
