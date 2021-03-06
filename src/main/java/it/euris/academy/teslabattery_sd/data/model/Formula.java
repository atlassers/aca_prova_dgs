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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.FormulaDto;
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
@Table(name = "formula")
@SQLDelete(sql = "UPDATE formula SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Formula implements Model{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @OneToOne
  @JoinColumn(name= "assembly_line_id")
  private AssemblyLine assemblyLineId;
  
  @OneToMany(mappedBy = "formulaId")
  @Builder.Default
  private Set<FormulaComponent> components = new HashSet<FormulaComponent>();
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public FormulaDto toDto() {
    FormulaDto result = FormulaDto.builder().id(UT.numberToString(id)).assemblyLineId(UT.numberToString(assemblyLineId.getId())).build();
    
    if(components != null) {
     result.getComponents().addAll(
         this.getComponents().stream().map(FormulaComponent::toDto).collect(Collectors.toSet()));
    }
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}