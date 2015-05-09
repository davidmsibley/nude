package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.column.NudeRow;

/**
 *
 * @author dmsibley
 */
public interface ColumnTransformer {
	public NudeRow transform(NudeRow row);
}
