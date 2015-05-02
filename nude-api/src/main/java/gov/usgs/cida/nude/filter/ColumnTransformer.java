package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.column.Value;

/**
 *
 * @author dmsibley
 */
public interface ColumnTransformer {
	public Value transform(Column from, Column to, NudeRow row);
}
