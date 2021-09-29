/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.FormulaComponentDto;
import it.euris.academy.teslabattery_sd.data.enums.Measure;
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
@Entity
@Table(name = "formula_component")
@SQLDelete(sql = "UPDATE formula_component SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class FormulaComponent implements Model{
  
  @EmbeddedId
  private FormulaComponentKey id;
  
  @ManyToOne
  @MapsId("formulaId")
  @JoinColumn(name = "formula_id")
  private Formula formulaId;
  
  @ManyToOne
  @MapsId("componentId")
  @JoinColumn(name = "component_id")
  private Component componentId;
  
  @Column(name="component_quantity")
  private Double componentQuantity;
  
  @Column(name="component_measure")
  private Measure componentMeasure;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public FormulaComponentDto toDto() {
    FormulaComponentDto result = FormulaComponentDto.builder()
        .componentId(UT.numberToString(componentId.getId()))
        .formulaId(UT.numberToString(formulaId.getId()))
        .componentQuantity(UT.numberToString(componentQuantity))
        .componentMeasure(UT.getMeasure(componentMeasure))
        .build();    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
