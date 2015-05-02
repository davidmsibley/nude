package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.NudeFilter;
import gov.usgs.cida.nude.column.NudeRow;

/**
 *
 * @author dmsibley
 */
public interface RowFlattener {
	public NudeRow flatten(NudeFilter in);
}
