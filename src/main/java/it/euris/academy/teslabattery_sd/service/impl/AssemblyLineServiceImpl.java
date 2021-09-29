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
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineDto;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.AssemblyLineRepository;
import it.euris.academy.teslabattery_sd.service.AssemblyLineService;

@Service
public class AssemblyLineServiceImpl implements AssemblyLineService{
  
  @Autowired
  AssemblyLineRepository assemblyLineRepository;

  @Override
  public Set<AssemblyLineDto> getAll() {
    return assemblyLineRepository.findAll().stream().map(AssemblyLine::toDto).collect(Collectors.toSet());
  }

  @Override
  public AssemblyLineDto get(Long id) {
    Optional<AssemblyLine> assemblyLine = assemblyLineRepository.findById(id);
    
    if(assemblyLine.isPresent()) {
      AssemblyLine assemblyLineToGet = assemblyLine.get();
      return assemblyLineToGet.toDto();
    }
    
    return null;
  }

  @Override
  public AssemblyLineDto post(AssemblyLineDto assemblyLineDto) {
    if(assemblyLineDto.getId() != null)
      throw new IdMustBeNullException();
    
    return assemblyLineRepository.save(assemblyLineDto.toModel()).toDto();
  }

  @Override
  public AssemblyLineDto put(AssemblyLineDto assemblyLineDto) {
    if(assemblyLineDto.getId() == null)
      throw new IdMustNotBeNullException();
    
    return assemblyLineRepository.save(assemblyLineDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    assemblyLineRepository.deleteById(id);
    return assemblyLineRepository.findById(id).isEmpty();
  }

}
