/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.ComponentDto;

public interface ComponentService {

  Set<ComponentDto> getAll();
  
  ComponentDto get(Long id);
  
  ComponentDto post(ComponentDto componentDto);
  
  ComponentDto put(ComponentDto componentDto);
  
  Boolean delete(Long id);
}
