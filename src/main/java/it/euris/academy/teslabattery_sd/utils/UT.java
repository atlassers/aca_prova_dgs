/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.utils;

import java.time.Instant;
import it.euris.academy.teslabattery_sd.data.enums.Measure;
import it.euris.academy.teslabattery_sd.data.enums.Status;
import it.euris.academy.teslabattery_sd.data.enums.Task;

public class UT {
  
  public static Integer toInteger(String value) {
    return value == null ? null : Integer.parseInt(value);
  }

  public static Long toLong(String value) {
    return value == null ? null : Long.parseLong(value);
  }

  public static Double toDouble(String value) {
    return value == null ? null : Double.parseDouble(value);
  }
  
  public static String numberToString(Number value) {
    return value == null ? null : value.toString();
  }

  public static Instant toInstant(String value) {
    return value == null ? null : Instant.parse(value);
  }

  public static String fromInstant(Instant value) {
    return value == null ? null : value.toString();
  }
  
  public static Task getTask(String value) {
    if (value==null) {
      return null;
    }
    for (Task task : Task.values()) {
      if (task.name().equals(value)) {
        return task;
      }
    }
    return null;
  }
  
  public static String getTask(Task value) {
    return value == null ? null : value.name();
  }
  
  public static Measure getMeasure(String value) {
    if (value==null) {
      return null;
    }
    for (Measure measure : Measure.values()) {
      if (measure.name().equals(value)) {
        return measure;
      }
    }
    return null;
  }
  
  public static String getMeasure(Measure value) {
    return value == null ? null : value.name();
  }
  
  public static Status getStatus(String value) {
    if (value==null) {
      return null;
    }
    for (Status status : Status.values()) {
      if (status.name().equals(value)) {
        return status;
      }
    }
    return null;
  }
  
  public static String getStatus(Status value) {
    return value == null ? null : value.name();
  }
}
