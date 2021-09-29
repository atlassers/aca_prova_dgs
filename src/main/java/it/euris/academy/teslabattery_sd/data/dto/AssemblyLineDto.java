/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssemblyLineDto implements Dto{

  private String id;
  private String name;
  private String maxTimeCompletion;
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public AssemblyLine toModel() {
    AssemblyLine result = AssemblyLine.builder().id(UT.toLong(id)).name(name).maxTimeCompletion(UT.toLong(maxTimeCompletion)).build();
    
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
