/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.ComponentDto;
import it.euris.academy.teslabattery_sd.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "component")
@SQLDelete(sql = "UPDATE component SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Component implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "hazardous")
  private Boolean hazardous;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @OneToMany (mappedBy = "componentId")
  @Builder.Default
  private Set<FormulaComponent> formulae = new HashSet<FormulaComponent>();


  @Override
  public ComponentDto toDto() {
    ComponentDto result =
        ComponentDto.builder().id(UT.numberToString(id)).name(name).hazardous(hazardous).build();
    
    if(formulae != null) {
      result.getFormulae().addAll(
          this.getFormulae().stream().map(FormulaComponent::toDto).collect(Collectors.toSet()));
    }
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
