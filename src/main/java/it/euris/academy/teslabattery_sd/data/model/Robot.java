/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.teslabattery_sd.data.archetype.Model;
import it.euris.academy.teslabattery_sd.data.dto.RobotDto;
import it.euris.academy.teslabattery_sd.data.enums.Task;
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
@Table(name = "robot")
@SQLDelete(sql = "UPDATE robot SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Robot implements Model {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "task")
  private Task task;
  
  @Column(name = "positional_order")
  private Integer positionalOrder;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @ManyToOne
  @JoinColumn(name = "assembly_line_id")
  private AssemblyLine assemblyLineId;

  @Override
  public RobotDto toDto() {
    RobotDto result = RobotDto.builder().id(UT.numberToString(id)).task(UT.getTask(task)).positionalOrder(UT.numberToString(positionalOrder)).description(description).build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
