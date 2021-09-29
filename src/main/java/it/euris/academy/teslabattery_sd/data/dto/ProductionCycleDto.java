/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.ProductionCycle;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductionCycleDto implements Dto{
  
  private String id;
  private String dateStart;
  private String status;
  private String dateLastStatusChange;
  @JsonIgnore
  private String dateEnd;
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public ProductionCycle toModel() {
    ProductionCycle result = ProductionCycle.builder()
        .id(UT.toLong(id))
        .dateStart(UT.toInstant(dateStart))
        .status(UT.getStatus(status))
        .dateLastStatusChange(UT.toInstant(dateLastStatusChange))
        .dateEnd(UT.toInstant(dateEnd))
        .build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
