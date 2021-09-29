/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;


import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.Formula;
import it.euris.academy.teslabattery_sd.data.model.FormulaComponent;
import it.euris.academy.teslabattery_sd.data.model.Component;
import it.euris.academy.teslabattery_sd.data.model.key.FormulaComponentKey;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormulaComponentDto implements Dto{
  
  private String formulaId;
  private String componentId;
  private String componentQuantity;
  private String componentMeasure;
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public FormulaComponent toModel() {
    Long formulaIdLong = UT.toLong(formulaId);
    Long componentIdLong = UT.toLong(componentId);
    
    FormulaComponent result = FormulaComponent.builder()
        .id(new FormulaComponentKey(formulaIdLong, componentIdLong))
        .formulaId(Formula.builder().id(formulaIdLong).build())
        .componentId(Component.builder().id(componentIdLong).build())
        .componentQuantity(UT.toDouble(componentQuantity))
        .componentMeasure(UT.getMeasure(componentMeasure))
        .build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
