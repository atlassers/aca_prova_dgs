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
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineDto;
import it.euris.academy.teslabattery_sd.service.AssemblyLineService;

@RestController
@RequestMapping("/assemblylines")
public class AssemblyLineController {
  
  @Autowired
  AssemblyLineService assemblyLineService;
  
  @GetMapping("/v1")
  public Set<AssemblyLineDto> getAll() {
    return assemblyLineService.getAll();
  }
  
  @GetMapping("/v1/{id}")
  public AssemblyLineDto get(@PathVariable("id") Long id) {
    return assemblyLineService.get(id);
  }
  
  @PostMapping("/v1")
  public AssemblyLineDto post(@RequestBody AssemblyLineDto assemblyLineDto) {
    return assemblyLineService.post(assemblyLineDto);
  }
  
  @PutMapping("/v1")
  public AssemblyLineDto put(@RequestBody AssemblyLineDto assemblyLineDto) {
    return assemblyLineService.put(assemblyLineDto);
  }
  
  @DeleteMapping("/v1/{id}")
  public Boolean delete(@PathVariable("id") Long id) {
    return assemblyLineService.delete(id);
  }

}
