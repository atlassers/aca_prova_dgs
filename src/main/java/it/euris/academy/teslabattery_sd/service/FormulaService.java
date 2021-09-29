/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.FormulaDto;

public interface FormulaService {
  
  Set<FormulaDto> getAll();
  
  FormulaDto get(Long id);
  
  FormulaDto post(FormulaDto formulaDto);
  
  FormulaDto put(FormulaDto formulaDto);
  
  Boolean delete(Long id);
}
