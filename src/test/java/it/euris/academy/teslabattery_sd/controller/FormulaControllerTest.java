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
import it.euris.academy.teslabattery_sd.data.dto.FormulaDto;
import it.euris.academy.teslabattery_sd.data.model.Formula;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.FormulaRepository;
import it.euris.academy.teslabattery_sd.service.FormulaService;
import it.euris.academy.teslabattery_sd.utils.TestSupport;

@SpringBootTest
public class FormulaControllerTest {


  @MockBean
  FormulaRepository formulaRepository;
  
  @Autowired
  FormulaService formulaService;
  
  @Test
  void getAll() {
    //arrange
    List<Formula> mockedFormulasList = List.of(TestSupport.createFormula(1L), TestSupport.createFormula(2L));
    when(formulaRepository.findAll()).thenReturn(mockedFormulasList);
    
    //act
    Set<FormulaDto> testedFormulasSet = formulaService.getAll();
    
    //assert
    assertEquals(mockedFormulasList.size(), testedFormulasSet.size());
  }
  
  @Test
  void getById() {
    //arrange
    Formula mockedFormula =TestSupport.createFormula(1L);
    when(formulaRepository.findById(1L)).thenReturn(Optional.of(mockedFormula));
    
    //act
    FormulaDto testedFormula = formulaService.get(1L);
    
    //assert
    assertEquals(mockedFormula.getId(), testedFormula.toModel().getId());
  }
  
  @Test
  void delete() {
    //arrange
    Long id = 12L;
    doNothing().when(formulaRepository).deleteById(id);
    when(formulaRepository.findById(id)).thenReturn(Optional.empty());
    
    //act+assert
    assertTrue(formulaService.delete(id));
    Mockito.verify(formulaRepository, times(1)).deleteById(id);
  }
  
  @Test
  void post() {
    //arrange
    Formula formulaToSave = TestSupport.createFormula(null);
    Formula formulaToReturn = TestSupport.createFormula(9L);
    when(formulaRepository.save(any())).thenReturn(formulaToReturn);
    
    //act
    FormulaDto testedStudent = formulaService.post(formulaToSave.toDto());
    
    //assert
    assertEquals(formulaToReturn.getId(), testedStudent.getId());
  }
  
  @Test
  void postWithNotNullId() {
  //arrange
    Formula formulaToSave = TestSupport.createFormula(1L);
    Formula formulaToReturn = TestSupport.createFormula(9L);
    when(formulaRepository.save(any())).thenReturn(formulaToReturn);
    
    
    //act+assert
    assertThrows(IdMustBeNullException.class, () -> {
      formulaService.post(formulaToSave.toDto());
    });
  }
  
  @Test
  void put() {
    //arrange
    Formula formulaToSave = TestSupport.createFormula(1L);
    Formula formulaToReturn = TestSupport.createFormula(9L);
    when(formulaRepository.save(any())).thenReturn(formulaToReturn);
    
    //act
    FormulaDto testedStudent = formulaService.put(formulaToSave.toDto());
    
    //assert
    assertEquals("9", testedStudent.getId());
  }
  
  @Test
  void putWithNullId() {
    //arrange
    Formula formulaToSave = TestSupport.createFormula(null);
    Formula formulaToReturn = TestSupport.createFormula(10L);
    when(formulaRepository.save(any())).thenReturn(formulaToReturn);
    
    //act+assert
    assertThrows(IdMustNotBeNullException.class, () -> {
      formulaService.put(formulaToSave.toDto());
    });
  }
 
}
