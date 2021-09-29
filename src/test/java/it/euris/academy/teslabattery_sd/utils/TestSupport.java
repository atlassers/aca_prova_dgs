/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.utils;

import it.euris.academy.teslabattery_sd.data.enums.Task;
import it.euris.academy.teslabattery_sd.data.model.Robot;

public class TestSupport {
  
  public static Robot createRobot(Long id) {
    return Robot.builder().id(id).task(Task.TESTING).description("tester")
        .positionalOrder(1).build();
  }
  
  

}
