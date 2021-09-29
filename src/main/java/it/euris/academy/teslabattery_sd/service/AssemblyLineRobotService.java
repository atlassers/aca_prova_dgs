/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineRobotDto;

public interface AssemblyLineRobotService {

  Set<AssemblyLineRobotDto> getAll();
  
  AssemblyLineRobotDto get(Long id);
  
  AssemblyLineRobotDto post(AssemblyLineRobotDto assemblyLineRobotDto);
  
  AssemblyLineRobotDto put(AssemblyLineRobotDto assemblyLineRobotDto);
  
  Boolean delete(Long id);
}
