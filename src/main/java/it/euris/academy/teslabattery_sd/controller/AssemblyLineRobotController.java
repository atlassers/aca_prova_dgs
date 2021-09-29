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
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineRobotDto;
import it.euris.academy.teslabattery_sd.service.AssemblyLineRobotService;

@RestController
@RequestMapping("/assembly-line-robots")
public class AssemblyLineRobotController {
  
  @Autowired
  AssemblyLineRobotService assemblyLineRobotService;
  
  @GetMapping("/v1")
  public Set<AssemblyLineRobotDto> getAll() {
    return assemblyLineRobotService.getAll();
  }
  
  @GetMapping("/v1/{assembly-line-id}-{robot-id}")
  public AssemblyLineRobotDto get(@PathVariable("assembly-line-id") Long assemblyLineId, @PathVariable("robot-id") Long robotId) {
    return assemblyLineRobotService.get(assemblyLineId, robotId);
  }
  
  @PostMapping("/v1")
  public AssemblyLineRobotDto post(@RequestBody AssemblyLineRobotDto assemblyLineRobotDto) {
    return assemblyLineRobotService.post(assemblyLineRobotDto);
  }
  
  @PutMapping("/v1")
  public AssemblyLineRobotDto put(@RequestBody AssemblyLineRobotDto assemblyLineRobotDto) {
    return assemblyLineRobotService.put(assemblyLineRobotDto);
  }
  
  @DeleteMapping("/v1/{assembly-line-id}-{robot-id}")
  public Boolean delete(@PathVariable("assembly-line-id") Long assemblyLineId, @PathVariable("robot-id") Long robotId) {
    return assemblyLineRobotService.delete(assemblyLineId, robotId);
  }
}
