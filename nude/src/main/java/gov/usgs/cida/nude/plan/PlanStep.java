package gov.usgs.cida.nude.plan;

import java.sql.ResultSet;

/**
 *
 * @author dmsibley
 */
public interface PlanStep {
	
	public ResultSet runStep(ResultSet input);
	
}
