/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.dto;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.teslabattery_sd.data.archetype.Dto;
import it.euris.academy.teslabattery_sd.data.model.Component;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ComponentDto implements Dto {

  private String id;
  private String name;
  private Boolean hazardous;
  @Builder.Default
  private Boolean deleted = false;
  @JsonIgnore
  @Builder.Default
  private Set<FormulaComponentDto> formulae = new HashSet<FormulaComponentDto>();

  @Override
  public Component toModel() {
    Component result = Component.builder().id(UT.toLong(id)).hazardous(hazardous).name(name).build();
    
    if(formulae != null) {
      result.getFormulae().addAll(
          this.getFormulae().stream().map(FormulaComponentDto::toModel).collect(Collectors.toSet()));
    }

    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }

    return result;
  }

}
