/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.List;
import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.ProductionCycleDto;
import it.euris.academy.teslabattery_sd.repository.projection.CycleStatisticsProjection;
import it.euris.academy.teslabattery_sd.repository.projection.WastedComponentsThisMonthStatisticsProjection;

public interface ProductionCycleService {

  Set<ProductionCycleDto> getAll();
  
  ProductionCycleDto get(Long id);
  
  ProductionCycleDto post(ProductionCycleDto productionCycleDto);
  
  ProductionCycleDto put(ProductionCycleDto productionCycleDto);
  
  Boolean delete(Long id);
  
  List<CycleStatisticsProjection> getNumberCyclesStatistics();
  
  List<WastedComponentsThisMonthStatisticsProjection> getWastedComponentsThisMonthStatistics();
}
