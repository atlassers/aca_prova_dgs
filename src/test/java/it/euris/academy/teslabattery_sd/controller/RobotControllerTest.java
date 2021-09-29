/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import it.euris.academy.teslabattery_sd.data.dto.RobotDto;
import it.euris.academy.teslabattery_sd.data.model.Robot;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.RobotRepository;
import it.euris.academy.teslabattery_sd.service.RobotService;
import it.euris.academy.teslabattery_sd.utils.TestSupport;


@SpringBootTest
public class RobotControllerTest {

  @MockBean
  RobotRepository robotRepository;
  
  @Autowired
  RobotService robotService;
  
  @Test
  void getAll() {
    //arrange
    List<Robot> mockedRobotsList = List.of(TestSupport.createRobot(1L), TestSupport.createRobot(2L));
    when(robotRepository.findAll()).thenReturn(mockedRobotsList);
    
    //act
    Set<RobotDto> testedRobotsSet = robotService.getAll();
    
    //assert
    assertEquals(mockedRobotsList.size(), testedRobotsSet.size());
  }
  
  @Test
  void getById() {
    //arrange
    Robot mockedRobot =TestSupport.createRobot(1L);
    when(robotRepository.findById(1L)).thenReturn(Optional.of(mockedRobot));
    
    //act
    RobotDto testedRobot = robotService.get(1L);
    
    //assert
    assertEquals(mockedRobot.getDescription(), testedRobot.toModel().getDescription());
  }
  
  @Test
  void delete() {
    //arrange
    Long id = 12L;
    doNothing().when(robotRepository).deleteById(id);
    when(robotRepository.findById(id)).thenReturn(Optional.empty());
    
    //act+assert
    assertTrue(robotService.delete(id));
    Mockito.verify(robotRepository, times(1)).deleteById(id);
  }
  
  @Test
  void post() {
    //arrange
    Robot robotToSave = TestSupport.createRobot(null);
    Robot robotToReturn = TestSupport.createRobot(9L);
    when(robotRepository.save(any())).thenReturn(robotToReturn);
    
    //act
    RobotDto testedStudent = robotService.post(robotToSave.toDto());
    
    //assert
    assertEquals(robotToReturn.getDescription(), testedStudent.getDescription());
  }
  
  @Test
  void postWithNotNullId() {
  //arrange
    Robot robotToSave = TestSupport.createRobot(1L);
    Robot robotToReturn = TestSupport.createRobot(9L);
    when(robotRepository.save(any())).thenReturn(robotToReturn);
    
    
    //act+assert
    assertThrows(IdMustBeNullException.class, () -> {
      robotService.post(robotToSave.toDto());
    });
  }
  
  @Test
  void put() {
    //arrange
    Robot robotToSave = TestSupport.createRobot(1L);
    Robot robotToReturn = TestSupport.createRobot(9L);
    when(robotRepository.save(any())).thenReturn(robotToReturn);
    
    //act
    RobotDto testedStudent = robotService.put(robotToSave.toDto());
    
    //assert
    assertEquals("9", testedStudent.getId());
  }
  
  @Test
  void putWithNullId() {
    //arrange
    Robot robotToSave = TestSupport.createRobot(null);
    Robot robotToReturn = TestSupport.createRobot(10L);
    when(robotRepository.save(any())).thenReturn(robotToReturn);
    
    //act+assert
    assertThrows(IdMustNotBeNullException.class, () -> {
      robotService.put(robotToSave.toDto());
    });
  }
 
}
