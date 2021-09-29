/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineDto;
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
@Table(name = "assembly_line")
@SQLDelete(sql = "UPDATE assembly_line SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class AssemblyLine implements Model{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "max_time")
  private Long maxTimeCompletion;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @OneToMany(mappedBy = "assemblyLineId")
  @Builder.Default
  private Set<AssemblyLineRobot> robots = new HashSet<AssemblyLineRobot>();
  
  @OneToOne(mappedBy = "assemblyLineId")
  private Formula formula;
  
  @OneToMany(mappedBy = "assemblyLineId")
  @Builder.Default
  private Set<ProductionCycle> productionCycle = new HashSet<ProductionCycle>();

  @Override
  public AssemblyLineDto toDto() {
    AssemblyLineDto result = AssemblyLineDto.builder().id(UT.numberToString(id)).name(name).maxTimeCompletion(UT.numberToString(maxTimeCompletion)).build();

    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
