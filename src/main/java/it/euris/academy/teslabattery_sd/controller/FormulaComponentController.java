/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.controller;

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
import it.euris.academy.teslabattery_sd.data.dto.FormulaComponentDto;
import it.euris.academy.teslabattery_sd.service.FormulaComponentService;

@RestController
@RequestMapping("/formula-components")
public class FormulaComponentController {
  
  @Autowired
  FormulaComponentService formulaComponentService;
  
  @GetMapping("/v1")
  public Set<FormulaComponentDto> getAll() {
    return formulaComponentService.getAll();
  }
  
  @GetMapping("/v1/{formula-id}-{component-id}")
  public FormulaComponentDto get(@PathVariable("formula-id") Long formulaId, @PathVariable("component-id") Long componentId) {
    return formulaComponentService.get(formulaId, formulaId);
  }
  
  @PostMapping("/v1")
  public FormulaComponentDto post(@RequestBody FormulaComponentDto formulaComponentDto) {
    return formulaComponentService.post(formulaComponentDto);
  }
  
  @PutMapping("/v1")
  public FormulaComponentDto put(@RequestBody FormulaComponentDto formulaComponentDto) {
    return formulaComponentService.put(formulaComponentDto);
  }
  
  @DeleteMapping("/v1/{formula-id}-{component-id}")
  public Boolean delete(@PathVariable("formula-id") Long formulaId, @PathVariable("component-id") Long componentId) {
    return formulaComponentService.delete(formulaId, formulaId);
  }
}
