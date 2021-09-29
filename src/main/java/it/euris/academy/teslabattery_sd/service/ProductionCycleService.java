/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.ProductionCycleDto;

public interface ProductionCycleService {

  Set<ProductionCycleDto> getAll();
  
  ProductionCycleDto get(Long id);
  
  ProductionCycleDto post(ProductionCycleDto productionCycleDto);
  
  ProductionCycleDto put(ProductionCycleDto productionCycleDto);
  
  Boolean delete(Long id);
}
