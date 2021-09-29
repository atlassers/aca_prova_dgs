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
import it.euris.academy.teslabattery_sd.data.dto.ComponentDto;
import it.euris.academy.teslabattery_sd.data.model.Component;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.ComponentRepository;
import it.euris.academy.teslabattery_sd.service.ComponentService;
import it.euris.academy.teslabattery_sd.utils.TestSupport;

@SpringBootTest
public class ComponentControllerTest {


  @MockBean
  ComponentRepository componentRepository;
  
  @Autowired
  ComponentService componentService;
  
  @Test
  void getAll() {
    //arrange
    List<Component> mockedComponentsList = List.of(TestSupport.createComponent(1L), TestSupport.createComponent(2L));
    when(componentRepository.findAll()).thenReturn(mockedComponentsList);
    
    //act
    Set<ComponentDto> testedComponentsSet = componentService.getAll();
    
    //assert
    assertEquals(mockedComponentsList.size(), testedComponentsSet.size());
  }
  
  @Test
  void getById() {
    //arrange
    Component mockedComponent =TestSupport.createComponent(1L);
    when(componentRepository.findById(1L)).thenReturn(Optional.of(mockedComponent));
    
    //act
    ComponentDto testedComponent = componentService.get(1L);
    
    //assert
    assertEquals(mockedComponent.getName(), testedComponent.toModel().getName());
  }
  
  @Test
  void delete() {
    //arrange
    Long id = 12L;
    doNothing().when(componentRepository).deleteById(id);
    when(componentRepository.findById(id)).thenReturn(Optional.empty());
    
    //act+assert
    assertTrue(componentService.delete(id));
    Mockito.verify(componentRepository, times(1)).deleteById(id);
  }
  
  @Test
  void post() {
    //arrange
    Component componentToSave = TestSupport.createComponent(null);
    Component componentToReturn = TestSupport.createComponent(9L);
    when(componentRepository.save(any())).thenReturn(componentToReturn);
    
    //act
    ComponentDto testedStudent = componentService.post(componentToSave.toDto());
    
    //assert
    assertEquals(componentToReturn.getName(), testedStudent.getName());
  }
  
  @Test
  void postWithNotNullId() {
  //arrange
    Component componentToSave = TestSupport.createComponent(1L);
    Component componentToReturn = TestSupport.createComponent(9L);
    when(componentRepository.save(any())).thenReturn(componentToReturn);
    
    
    //act+assert
    assertThrows(IdMustBeNullException.class, () -> {
      componentService.post(componentToSave.toDto());
    });
  }
  
  @Test
  void put() {
    //arrange
    Component componentToSave = TestSupport.createComponent(1L);
    Component componentToReturn = TestSupport.createComponent(9L);
    when(componentRepository.save(any())).thenReturn(componentToReturn);
    
    //act
    ComponentDto testedStudent = componentService.put(componentToSave.toDto());
    
    //assert
    assertEquals("9", testedStudent.getId());
  }
  
  @Test
  void putWithNullId() {
    //arrange
    Component componentToSave = TestSupport.createComponent(null);
    Component componentToReturn = TestSupport.createComponent(10L);
    when(componentRepository.save(any())).thenReturn(componentToReturn);
    
    //act+assert
    assertThrows(IdMustNotBeNullException.class, () -> {
      componentService.put(componentToSave.toDto());
    });
  }
 
}
