/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.euris.academy.teslabattery_sd.data.dto.ProductionCycleDto;
import it.euris.academy.teslabattery_sd.repository.projection.CycleStatisticsProjection;
import it.euris.academy.teslabattery_sd.repository.projection.WastedComponentsThisMonthStatisticsProjection;
import it.euris.academy.teslabattery_sd.service.ProductionCycleService;

@RestController
@RequestMapping("/production-cycles")
public class ProductionCycleController {
  
  @Autowired
  ProductionCycleService productionCycleService;
  
  @GetMapping("/v1")
  public Set<ProductionCycleDto> getAll() {
    return productionCycleService.getAll();
  }
  
  @GetMapping("/v1/{id}")
  public ProductionCycleDto get(@PathVariable("id") Long id) {
    return productionCycleService.get(id);
  }
  
  @GetMapping("/v1/number-cycles-statistics")
  public CycleStatisticsProjection getNumberCyclesStatistics() {
    return productionCycleService.getNumberCyclesStatistics();
  }
  
  @GetMapping("/v1/wasted-components-this-month")
  public List<WastedComponentsThisMonthStatisticsProjection> getWastedComponentsThisMonthStatistics() {
    return productionCycleService.getWastedComponentsThisMonthStatistics();
  }
  
  @PostMapping("/v1")
  public ProductionCycleDto post(@RequestBody ProductionCycleDto productionCycleDto) {
    return productionCycleService.post(productionCycleDto);
  }
  
  @PutMapping("/v1")
  public ProductionCycleDto put(@RequestBody ProductionCycleDto productionCycleDto) {
    return productionCycleService.put(productionCycleDto);
  }
  
  @DeleteMapping("/v1/{id}")
  public Boolean delete(@PathVariable("id") Long id) {
    return productionCycleService.delete(id);
  }
}
