package gov.usgs.cida.nude.filter.api;

import gov.usgs.cida.nude.filter.RowFlattener;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class KeyFlattener implements RowFlattener {
	private static final Logger log = LoggerFactory.getLogger(KeyFlattener.class);

	@Override
	public TableRow flatten(ResultSet in) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
