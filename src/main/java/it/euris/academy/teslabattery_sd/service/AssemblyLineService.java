/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineDto;

public interface AssemblyLineService {
  
  Set<AssemblyLineDto> getAll();
  
  AssemblyLineDto get(Long id);
  
  AssemblyLineDto post(AssemblyLineDto assemblyLineDto);
  
  AssemblyLineDto put(AssemblyLineDto assemblyLineDto);
  
  Boolean delete(Long id);
}
