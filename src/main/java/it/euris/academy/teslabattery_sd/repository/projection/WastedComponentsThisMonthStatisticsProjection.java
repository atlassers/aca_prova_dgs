/**
 * @author Stefano De Giorgi
 * @since 2021-10-03
 */

package it.euris.academy.teslabattery_sd.repository.projection;

public interface WastedComponentsThisMonthStatisticsProjection {
  
  String getComponentNames();
  
  Double getComponentQuantities();
  
  String getComponentMeasures();
}
