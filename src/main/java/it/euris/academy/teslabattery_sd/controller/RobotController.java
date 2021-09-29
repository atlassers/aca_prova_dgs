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
import it.euris.academy.teslabattery_sd.data.dto.RobotDto;
import it.euris.academy.teslabattery_sd.service.RobotService;

@RestController
@RequestMapping("/robots")
public class RobotController {
  
  @Autowired
  RobotService robotService;
  
  @GetMapping("/v1")
  public Set<RobotDto> getAll() {
    return robotService.getAll();
  }
  
  @GetMapping("/v1/{id}")
  public RobotDto get(@PathVariable("id") Long id) {
    return robotService.get(id);
  }
  
  @PostMapping("/v1")
  public RobotDto post(@RequestBody RobotDto robotDto) {
    return robotService.post(robotDto);
  }
  
  @PutMapping("/v1")
  public RobotDto put(@RequestBody RobotDto robotDto) {
    return robotService.put(robotDto);
  }
  
  @DeleteMapping("/v1/{id}")
  public Boolean delete(@PathVariable("id") Long id) {
    return robotService.delete(id);
  }
}
