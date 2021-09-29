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
import it.euris.academy.teslabattery_sd.data.dto.ProductionCycleDto;
import it.euris.academy.teslabattery_sd.data.model.ProductionCycle;
import it.euris.academy.teslabattery_sd.exception.IdMustBeNullException;
import it.euris.academy.teslabattery_sd.exception.IdMustNotBeNullException;
import it.euris.academy.teslabattery_sd.repository.ProductionCycleRepository;
import it.euris.academy.teslabattery_sd.service.ProductionCycleService;

@Service
public class ProductionCycleServiceImpl implements ProductionCycleService{
  
  @Autowired
  ProductionCycleRepository productionCycleRepository;

  @Override
  public Set<ProductionCycleDto> getAll() {
    return productionCycleRepository.findAll().stream().map(ProductionCycle::toDto).collect(Collectors.toSet());
  }

  @Override
  public ProductionCycleDto get(Long id) {
    Optional<ProductionCycle> productionCycle = productionCycleRepository.findById(id);
    
    if(productionCycle.isPresent()) {
      ProductionCycle productionCycleToGet = productionCycle.get();
      return productionCycleToGet.toDto();
    }
    
    return null;
  }

  @Override
  public ProductionCycleDto post(ProductionCycleDto productionCycleDto) {
    if(productionCycleDto.getId() != null)
      throw new IdMustBeNullException();
    
    return productionCycleRepository.save(productionCycleDto.toModel()).toDto();
  }

  @Override
  public ProductionCycleDto put(ProductionCycleDto productionCycleDto) {
    if(productionCycleDto.getId() == null)
      throw new IdMustNotBeNullException();
    
    return productionCycleRepository.save(productionCycleDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    productionCycleRepository.deleteById(id);
    return productionCycleRepository.findById(id).isEmpty();
  }

}