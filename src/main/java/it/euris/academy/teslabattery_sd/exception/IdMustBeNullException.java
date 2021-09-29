/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.exception;

public class IdMustBeNullException extends RuntimeException{

  public IdMustBeNullException() {
    super("Id must be null. You sent a dto with an id already present");
  }
  public IdMustBeNullException(String message) {
    super(message);
  }
}
