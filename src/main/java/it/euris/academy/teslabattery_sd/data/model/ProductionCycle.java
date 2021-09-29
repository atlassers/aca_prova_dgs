/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.ProductionCycleDto;
import it.euris.academy.teslabattery_sd.data.enums.Status;
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
@Table(name = "production_cycle")
@SQLDelete(sql = "UPDATE production_cycle SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ProductionCycle implements Model{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "assembly_line_id")
  @JsonIgnore
  private AssemblyLine assemblyLineId;
  
  @Column(name = "date_start")
  private Instant dateStart;
  
  @Column(name = "status")
  @Enumerated(value = EnumType.STRING)
  private Status status;
  
  @Column(name = "date_last_status_change")
  private Instant dateLastStatusChange;
  
  @Column(name = "date_end")
  private Instant dateEnd;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public ProductionCycleDto toDto() {
    ProductionCycleDto result = ProductionCycleDto.builder()
        .id(UT.numberToString(id))
        .dateStart(UT.fromInstant(dateStart))
        .status(UT.getStatus(status))
        .dateLastStatusChange(UT.fromInstant(dateLastStatusChange))
        .dateEnd(UT.fromInstant(dateEnd))
        .build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
