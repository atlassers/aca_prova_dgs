/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import java.util.Map;
import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;
import it.euris.academy.teslabattery_sd.data.model.Formula;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormulaDto implements Dto{
  
  private String id;
  private String assemblyLineId;
  private Map<String, String> components;
  private Map<String, String> componentQuantity;
  private Map<String, String> componentMeasure;
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public Formula toModel() {
    Formula result = Formula.builder().id(UT.toLong(id)).build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
