/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.RobotDto;

public interface RobotService {

  Set<RobotDto> getAll();
  
  RobotDto get(Long id);
  
  RobotDto post(RobotDto robotDto);
  
  RobotDto put(RobotDto robotDto);
  
  Boolean delete(Long id);
}
