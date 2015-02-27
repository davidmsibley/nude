package gov.usgs.cida.nude.filter.api;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.filter.ColumnTransform;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class Drop implements ColumnTransform {

	private static final Logger log = LoggerFactory.getLogger(Drop.class);

	private Drop() {

	}
	@Override
	public String transform(TableRow row) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public static Drop column(Column col) {
		Drop result = new Drop();
		//TODO
		return result;
	}
}
