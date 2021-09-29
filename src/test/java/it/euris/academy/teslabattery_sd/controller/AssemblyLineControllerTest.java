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
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineDto;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.AssemblyLineRepository;
import it.euris.academy.teslabattery_sd.service.AssemblyLineService;
import it.euris.academy.teslabattery_sd.utils.TestSupport;

@SpringBootTest
public class AssemblyLineControllerTest {



  @MockBean
  AssemblyLineRepository assemblyLineRepository;
  
  @Autowired
  AssemblyLineService assemblyLineService;
  
  @Test
  void getAll() {
    //arrange
    List<AssemblyLine> mockedAssemblyLinesList = List.of(TestSupport.createAssemblyLine(1L), TestSupport.createAssemblyLine(2L));
    when(assemblyLineRepository.findAll()).thenReturn(mockedAssemblyLinesList);
    
    //act
    Set<AssemblyLineDto> testedAssemblyLinesSet = assemblyLineService.getAll();
    
    //assert
    assertEquals(mockedAssemblyLinesList.size(), testedAssemblyLinesSet.size());
  }
  
  @Test
  void getById() {
    //arrange
    AssemblyLine mockedAssemblyLine =TestSupport.createAssemblyLine(1L);
    when(assemblyLineRepository.findById(1L)).thenReturn(Optional.of(mockedAssemblyLine));
    
    //act
    AssemblyLineDto testedAssemblyLine = assemblyLineService.get(1L);
    
    //assert
    assertEquals(mockedAssemblyLine.getName(), testedAssemblyLine.toModel().getName());
  }
  
  @Test
  void delete() {
    //arrange
    Long id = 12L;
    doNothing().when(assemblyLineRepository).deleteById(id);
    when(assemblyLineRepository.findById(id)).thenReturn(Optional.empty());
    
    //act+assert
    assertTrue(assemblyLineService.delete(id));
    Mockito.verify(assemblyLineRepository, times(1)).deleteById(id);
  }
  
  @Test
  void post() {
    //arrange
    AssemblyLine assemblyLineToSave = TestSupport.createAssemblyLine(null);
    AssemblyLine assemblyLineToReturn = TestSupport.createAssemblyLine(9L);
    when(assemblyLineRepository.save(any())).thenReturn(assemblyLineToReturn);
    
    //act
    AssemblyLineDto testedStudent = assemblyLineService.post(assemblyLineToSave.toDto());
    
    //assert
    assertEquals(assemblyLineToReturn.getName(), testedStudent.getName());
  }
  
  @Test
  void postWithNotNullId() {
  //arrange
    AssemblyLine assemblyLineToSave = TestSupport.createAssemblyLine(1L);
    AssemblyLine assemblyLineToReturn = TestSupport.createAssemblyLine(9L);
    when(assemblyLineRepository.save(any())).thenReturn(assemblyLineToReturn);
    
    
    //act+assert
    assertThrows(IdMustBeNullException.class, () -> {
      assemblyLineService.post(assemblyLineToSave.toDto());
    });
  }
  
  @Test
  void put() {
    //arrange
    AssemblyLine assemblyLineToSave = TestSupport.createAssemblyLine(1L);
    AssemblyLine assemblyLineToReturn = TestSupport.createAssemblyLine(9L);
    when(assemblyLineRepository.save(any())).thenReturn(assemblyLineToReturn);
    
    //act
    AssemblyLineDto testedStudent = assemblyLineService.put(assemblyLineToSave.toDto());
    
    //assert
    assertEquals("9", testedStudent.getId());
  }
  
  @Test
  void putWithNullId() {
    //arrange
    AssemblyLine assemblyLineToSave = TestSupport.createAssemblyLine(null);
    AssemblyLine assemblyLineToReturn = TestSupport.createAssemblyLine(10L);
    when(assemblyLineRepository.save(any())).thenReturn(assemblyLineToReturn);
    
    //act+assert
    assertThrows(IdMustNotBeNullException.class, () -> {
      assemblyLineService.put(assemblyLineToSave.toDto());
    });
  }
 
}
