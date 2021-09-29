/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.FormulaDto;
import it.euris.academy.teslabattery_sd.data.enums.Measure;
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
  
  @Column
  @Builder.Default
  private Map<Long, Component> components = new HashMap<>();
  
  @Column(name="component_quantity")
  @Builder.Default
  private Map<Long, Double> componentQuantity = new HashMap<>();
  
  @Column(name = "component_measure")
  @Builder.Default
  private Map<Long,Measure> componentMeasure = new HashMap<>();
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public FormulaDto toDto() {
    FormulaDto result = FormulaDto.builder().id(UT.numberToString(id)).build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
