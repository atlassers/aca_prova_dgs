/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.euris.academy.teslabattery_sd.data.model.AssemblyLine;

public interface AssemblyLineRepository extends JpaRepository<AssemblyLine, Long>{

}
