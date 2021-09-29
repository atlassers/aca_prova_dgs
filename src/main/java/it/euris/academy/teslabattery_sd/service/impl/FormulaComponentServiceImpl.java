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
import it.euris.academy.teslabattery_sd.data.dto.FormulaComponentDto;
import it.euris.academy.teslabattery_sd.data.model.FormulaComponent;
import it.euris.academy.teslabattery_sd.data.model.key.FormulaComponentKey;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.FormulaComponentRepository;
import it.euris.academy.teslabattery_sd.service.FormulaComponentService;

@Service
public class FormulaComponentServiceImpl implements FormulaComponentService{

  
  @Autowired
  FormulaComponentRepository formulaComponentRepository;

  @Override
  public Set<FormulaComponentDto> getAll() {
    return formulaComponentRepository.findAll().stream().map(FormulaComponent::toDto).collect(Collectors.toSet());
  }

  @Override
  public FormulaComponentDto get(Long formulaId, Long componentId) {
    FormulaComponentKey id = new FormulaComponentKey(formulaId, componentId);
    Optional<FormulaComponent> formulaComponent = formulaComponentRepository.findById(id);
    
    if(formulaComponent.isPresent()) {
      FormulaComponent formulaComponentToGet = formulaComponent.get();
      return formulaComponentToGet.toDto();
    }
    
    return null;
  }

  @Override
  public FormulaComponentDto post(FormulaComponentDto formulaComponentDto) { 
    if(formulaComponentDto.getFormulaId() == null)
      throw new IdMustNotBeNullException();
    
    if(formulaComponentDto.getComponentId() == null)
      throw new IdMustNotBeNullException();
    
    
    return formulaComponentRepository.save(formulaComponentDto.toModel()).toDto();
  }

  @Override
  public FormulaComponentDto put(FormulaComponentDto formulaComponentDto) {
    if(formulaComponentDto.getFormulaId() == null)
      throw new IdMustNotBeNullException();
    
    if(formulaComponentDto.getComponentId() == null)
      throw new IdMustNotBeNullException();
    
    return formulaComponentRepository.save(formulaComponentDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long formulaId, Long componentId) {
    FormulaComponentKey id = new FormulaComponentKey(formulaId, componentId);
    formulaComponentRepository.deleteById(id);
    return formulaComponentRepository.findById(id).isEmpty();
  }

}
