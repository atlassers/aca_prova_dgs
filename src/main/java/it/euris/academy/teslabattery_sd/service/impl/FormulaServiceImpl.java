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
import it.euris.academy.teslabattery_sd.data.dto.FormulaDto;
import it.euris.academy.teslabattery_sd.data.model.Formula;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.FormulaRepository;
import it.euris.academy.teslabattery_sd.service.FormulaService;

@Service
public class FormulaServiceImpl implements FormulaService{
  
  @Autowired
  FormulaRepository formulaRepository;

  @Override
  public Set<FormulaDto> getAll() {
    return formulaRepository.findAll().stream().map(Formula::toDto).collect(Collectors.toSet());
  }

  @Override
  public FormulaDto get(Long id) {
    Optional<Formula> formula = formulaRepository.findById(id);
    
    if(formula.isPresent()) {
      Formula formulaToGet = formula.get();
      return formulaToGet.toDto();
    }
    
    return null;
  }

  @Override
  public FormulaDto post(FormulaDto formulaDto) {
    if(formulaDto.getId() != null)
      throw new IdMustBeNullException();
    
    return formulaRepository.save(formulaDto.toModel()).toDto();
  }

  @Override
  public FormulaDto put(FormulaDto formulaDto) {
    if(formulaDto.getId() == null)
      throw new IdMustNotBeNullException();
    
    return formulaRepository.save(formulaDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    formulaRepository.deleteById(id);
    return formulaRepository.findById(id).isEmpty();
  }

}
