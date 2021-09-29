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
import it.euris.academy.teslabattery_sd.data.dto.RobotDto;
import it.euris.academy.teslabattery_sd.data.model.Robot;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.RobotRepository;
import it.euris.academy.teslabattery_sd.service.RobotService;

@Service
public class RobotServiceImpl implements RobotService{
  
  @Autowired
  RobotRepository robotRepository;

  @Override
  public Set<RobotDto> getAll() {
    return robotRepository.findAll().stream().map(Robot::toDto).collect(Collectors.toSet());
  }

  @Override
  public RobotDto get(Long id) {
    Optional<Robot> robot = robotRepository.findById(id);
    
    if(robot.isPresent()) {
      Robot robotToGet = robot.get();
      return robotToGet.toDto();
    }
    
    return null;
  }

  @Override
  public RobotDto post(RobotDto robotDto) {
    if(robotDto.getId() != null)
      throw new IdMustBeNullException();
    
    return robotRepository.save(robotDto.toModel()).toDto();
  }

  @Override
  public RobotDto put(RobotDto robotDto) {
    if(robotDto.getId() == null)
      throw new IdMustNotBeNullException();
    
    return robotRepository.save(robotDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    robotRepository.deleteById(id);
    return robotRepository.findById(id).isEmpty();
  }

}
