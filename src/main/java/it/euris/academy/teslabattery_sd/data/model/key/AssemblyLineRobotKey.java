/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.data.model.key;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AssemblyLineRobotKey implements Serializable{
  
  @Column(name = "assembly_line_id")
  private Long assemblyLineId;
  
  @Column(name = "robot_id")
  private Long robotId;

}
