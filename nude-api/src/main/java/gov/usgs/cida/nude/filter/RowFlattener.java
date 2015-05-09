package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.NudeSource;
import gov.usgs.cida.nude.column.NudeRow;

/**
 *
 * @author dmsibley
 */
public interface RowFlattener {
	public NudeRow flatten(NudeSource in);
}
