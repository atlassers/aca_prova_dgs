/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.Robot;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RobotDto implements Dto{
  
  private String id;
  private String task;
  private String positionalOrder;
  private String description;
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public Robot toModel() {
    Robot result = Robot.builder().id(UT.toLong(id)).task(UT.getTask(task)).positionalOrder(UT.toInteger(positionalOrder)).description(description).build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
