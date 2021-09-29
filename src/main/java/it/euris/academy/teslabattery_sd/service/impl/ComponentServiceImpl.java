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
import it.euris.academy.teslabattery_sd.data.dto.ComponentDto;
import it.euris.academy.teslabattery_sd.data.model.Component;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.ComponentRepository;
import it.euris.academy.teslabattery_sd.service.ComponentService;

@Service
public class ComponentServiceImpl implements ComponentService{
  
  @Autowired
  ComponentRepository componentRepository;

  @Override
  public Set<ComponentDto> getAll() {
    return componentRepository.findAll().stream().map(Component::toDto).collect(Collectors.toSet());
  }

  @Override
  public ComponentDto get(Long id) {
    Optional<Component> component = componentRepository.findById(id);
    
    if(component.isPresent()) {
      Component componentToGet = component.get();
      return componentToGet.toDto();
    }
    
    return null;
  }

  @Override
  public ComponentDto post(ComponentDto componentDto) {
    if(componentDto.getId() != null)
      throw new IdMustBeNullException();
    
    return componentRepository.save(componentDto.toModel()).toDto();
  }

  @Override
  public ComponentDto put(ComponentDto componentDto) {
    if(componentDto.getId() == null)
      throw new IdMustNotBeNullException();
    
    return componentRepository.save(componentDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    componentRepository.deleteById(id);
    return componentRepository.findById(id).isEmpty();
  }

}
