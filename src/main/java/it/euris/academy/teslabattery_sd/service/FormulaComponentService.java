/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.service;

import java.util.Set;
import it.euris.academy.teslabattery_sd.data.dto.FormulaComponentDto;

public interface FormulaComponentService {

  Set<FormulaComponentDto> getAll();
  
  FormulaComponentDto get(Long id);
  
  FormulaComponentDto post(FormulaComponentDto formulaComponentDto);
  
  FormulaComponentDto put(FormulaComponentDto formulaComponentDto);
  
  Boolean delete(Long id);
}
