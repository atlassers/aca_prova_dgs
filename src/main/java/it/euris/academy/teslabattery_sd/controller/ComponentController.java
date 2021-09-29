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
import it.euris.academy.teslabattery_sd.data.dto.ComponentDto;
import it.euris.academy.teslabattery_sd.service.ComponentService;

@RestController
@RequestMapping("/components")
public class ComponentController {
  
  @Autowired
  ComponentService componentService;
  
  @GetMapping("/v1")
  public Set<ComponentDto> getAll() {
    return componentService.getAll();
  }
  
  @GetMapping("/v1/{id}")
  public ComponentDto get(@PathVariable("id") Long id) {
    return componentService.get(id);
  }
  
  @PostMapping("/v1")
  public ComponentDto post(@RequestBody ComponentDto componentDto) {
    return componentService.post(componentDto);
  }
  
  @PutMapping("/v1")
  public ComponentDto put(@RequestBody ComponentDto componentDto) {
    return componentService.put(componentDto);
  }
  
  @DeleteMapping("/v1/{id}")
  public Boolean delete(@PathVariable("id") Long id) {
    return componentService.delete(id);
  }
}
