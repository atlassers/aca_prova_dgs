/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.utils;

import it.euris.academy.teslabattery_sd.data.enums.Task;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;
import it.euris.academy.teslabattery_sd.data.model.Component;
import it.euris.academy.teslabattery_sd.data.model.Formula;
import it.euris.academy.teslabattery_sd.data.model.Robot;

public class TestSupport {
  
  public static Robot createRobot(Long id) {
    return Robot.builder().id(id).task(Task.TESTING).description("tester")
        .positionalOrder(1).build();
  }
  
  public static AssemblyLine createAssemblyLine(Long id) {
    return AssemblyLine.builder()
        .id(id)
        .name("test")
        .maxTimeCompletion(10L)
        .build();
  }
  
  public static Component createComponent(Long id) {
    return Component.builder()
        .id(id)
        .name("test")
        .hazardous(Boolean.FALSE)
        .build();
  }
  
  public static Formula createFormula(Long id) {
    return Formula.builder()
        .id(id)
        .build();
  }

}
