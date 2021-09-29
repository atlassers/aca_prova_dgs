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
import it.euris.academy.teslabattery_sd.data.dto.AssemblyLineRobotDto;
import it.euris.academy.teslabattery_sd.data.model.key.AssemblyLineRobotKey;
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
@Table(name = "assembly_line_robot")
@SQLDelete(sql = "UPDATE assembly_line_robot SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class AssemblyLineRobot implements Model{
  
  @EmbeddedId
  private AssemblyLineRobotKey id;
  
  @ManyToOne
  @MapsId("assemblyLineId")
  @JoinColumn(name = "assembly_line_id")
  private AssemblyLine assemblyLineId;
  
  @ManyToOne
  @MapsId("robotId")
  @JoinColumn(name = "robot_id")
  private Robot robotId;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @Override
  public AssemblyLineRobotDto toDto() {
    AssemblyLineRobotDto result = AssemblyLineRobotDto.builder()
        .assemblyLineId(UT.numberToString(assemblyLineId.getId()))
        .robotId(UT.numberToString(robotId.getId()))
        .build();
    
    if (deleted == Boolean.TRUE) {
      result.setDeleted(deleted);
    }
    
    return result;
  }

}
