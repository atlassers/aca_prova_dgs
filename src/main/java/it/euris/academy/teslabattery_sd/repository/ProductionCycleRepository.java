/**
 * @author Stefano De Giorgi
 * @since 2021-09-29
 */

package it.euris.academy.teslabattery_sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.euris.academy.teslabattery_sd.data.model.ProductionCycle;
import it.euris.academy.teslabattery_sd.repository.projection.CycleStatisticsProjection;

public interface ProductionCycleRepository extends JpaRepository<ProductionCycle, Long>{
  
  @Query(value = 
      "SELECT SUM(CASE WHEN p.status='COMPLETED' THEN 1 ELSE 0 END) as monthlyCompletedCycles, "
      + "    SUM(CASE WHEN p.status='FAILED' THEN 1 ELSE 0 END) as monthlyFailedCycles, "
      + "    COUNT(p.status) as monthlyTotalCycles, "
      + "    ROUND(CAST(( SUM(CASE WHEN p.status='COMPLETED' THEN 1 ELSE 0 END) * 100.0 / COUNT(p.status)) AS FLOAT), 2) AS cycleSuccessPercentage "
      + "FROM teslabattery_sd.production_cycle p "
      + "WHERE p.deleted=0 OR p.deleted=1;" , nativeQuery = true)
  CycleStatisticsProjection getNumberCyclesStatistics();

}
