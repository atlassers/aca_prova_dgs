/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.euris.academy.teslabattery_sd.data.model.ProductionCycle;
import it.euris.academy.teslabattery_sd.repository.projection.CycleStatisticsProjection;
import it.euris.academy.teslabattery_sd.repository.projection.WastedComponentsThisMonthStatisticsProjection;

public interface ProductionCycleRepository extends JpaRepository<ProductionCycle, Long>{
  
  @Query(value = 
      "SELECT SUM(CASE WHEN p.status='COMPLETED' THEN 1 ELSE 0 END) AS monthlyCompletedCycles, "
      + "    SUM(CASE WHEN p.status='FAILED' THEN 1 ELSE 0 END) AS monthlyFailedCycles, "
      + "    COUNT(p.status) AS monthlyTotalCycles, "
      + "    ROUND(CAST(( SUM(CASE WHEN p.status='COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(p.status)) AS FLOAT), 2) AS cycleSuccessPercentage, "
      + "    MONTH(p.date_end) AS month"
      + "FROM production_cycle p "
      + "WHERE p.deleted=0 OR p.deleted=1 "
      + "GROUP BY MONTH(p.date_end) "
      + "ORDER BY MONTH(p.date_end)" , nativeQuery = true)
  CycleStatisticsProjection getNumberCyclesStatistics();

  @Query(value =
      "SELECT c.name AS componentNames, fc.component_quantity AS componentQuantities, fc.component_measure AS componentMeasures "
      + "FROM production_cycle p "
      + "    JOIN assembly_line a ON p.assembly_line_id = a.id "
      + "    JOIN formula f ON f.assembly_line_id = a.id "
      + "    JOIN formula_component fc ON fc.formula_id = f.id "
      + "    JOIN component c ON c.id = fc.component_id "
      + "WHERE p.status='FAILED' AND MONTH(p.date_end)=10", nativeQuery = true)
  List<WastedComponentsThisMonthStatisticsProjection> getWastedComponentsThisMonthStatistics();
}
