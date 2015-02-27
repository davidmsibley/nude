package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import java.sql.ResultSet;

/**
 *
 * @author dmsibley
 */
public interface RowFlattener {
	public TableRow flatten(ResultSet in);
}
