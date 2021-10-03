/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @Builder.Default
  private Boolean deleted = false;
  @JsonIgnore
  @Builder.Default
  private Set<FormulaComponentDto> components = new HashSet<>();

  @Override
  public Formula toModel() {
    Formula result = Formula.builder().id(UT.toLong(id)).assemblyLineId(AssemblyLine.builder().id(UT.toLong(assemblyLineId)).build())
        .build();
    
    if(components != null) {
      result.getComponents().addAll(
          this.getComponents().stream().map(FormulaComponentDto::toModel).collect(Collectors.toSet()));
    }
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
