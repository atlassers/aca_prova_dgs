/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineRobotDto;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLineRobot;
import it.euris.academy.teslabattery_sd.data.model.key.AssemblyLineRobotKey;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.AssemblyLineRobotRepository;
import it.euris.academy.teslabattery_sd.service.AssemblyLineRobotService;

@Service
public class AssemblyLineRobotServiceImpl implements AssemblyLineRobotService{
  
  @Autowired
  AssemblyLineRobotRepository assemblyLineRobotRepository;

  @Override
  public Set<AssemblyLineRobotDto> getAll() {
    return assemblyLineRobotRepository.findAll().stream().map(AssemblyLineRobot::toDto).collect(Collectors.toSet());
  }

  @Override
  public AssemblyLineRobotDto get(Long assemblyLineId, Long robotId) {
    AssemblyLineRobotKey id = new AssemblyLineRobotKey(assemblyLineId, robotId);
    Optional<AssemblyLineRobot> assemblyLineRobot = assemblyLineRobotRepository.findById(id);
    
    if(assemblyLineRobot.isPresent()) {
      AssemblyLineRobot assemblyLineRobotToGet = assemblyLineRobot.get();
      return assemblyLineRobotToGet.toDto();
    }
    
    return null;
  }

  @Override
  public AssemblyLineRobotDto post(AssemblyLineRobotDto assemblyLineRobotDto) {
    if(assemblyLineRobotDto.getAssemblyLineId() != null)
      throw new IdMustBeNullException();
    
    if(assemblyLineRobotDto.getRobotId() != null)
      throw new IdMustBeNullException();
    
    return assemblyLineRobotRepository.save(assemblyLineRobotDto.toModel()).toDto();
  }

  @Override
  public AssemblyLineRobotDto put(AssemblyLineRobotDto assemblyLineRobotDto) {
    if(assemblyLineRobotDto.getAssemblyLineId() == null)
      throw new IdMustNotBeNullException();
    
    if(assemblyLineRobotDto.getRobotId() == null)
      throw new IdMustNotBeNullException();
    
    return assemblyLineRobotRepository.save(assemblyLineRobotDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long assemblyLineId, Long robotId) {
    AssemblyLineRobotKey id = new AssemblyLineRobotKey(assemblyLineId, robotId);
    assemblyLineRobotRepository.deleteById(id);
    return assemblyLineRobotRepository.findById(id).isEmpty();
  }

}
