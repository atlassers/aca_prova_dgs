/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLineRobot;
import it.euris.academy.teslabattery_sd.data.model.Robot;
import it.euris.academy.teslabattery_sd.data.model.key.AssemblyLineRobotKey;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssemblyLineRobotDto implements Dto{
  
  private String assemblyLineId;
  private String robotId;
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public AssemblyLineRobot toModel() {
    Long assemblyLineIdLong = UT.toLong(assemblyLineId);
    Long robotIdLong = UT.toLong(robotId);
    
    AssemblyLineRobot result = AssemblyLineRobot.builder()
        .id(new AssemblyLineRobotKey(assemblyLineIdLong, robotIdLong))
        .assemblyLineId(AssemblyLine.builder().id(assemblyLineIdLong).build())
        .robotId(Robot.builder().id(robotIdLong).build())
        .build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
