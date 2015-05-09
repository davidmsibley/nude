package gov.usgs.cida.nude.filter.column;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.filter.ColumnTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class Drop implements ColumnTransformer {
	private static final Logger log = LoggerFactory.getLogger(Drop.class);
	private final Column column;
	
	private Drop(Column column) {
		this.column = column;
	}
	
	public static Drop column(Column column) {
		return new Drop(column);
	}

	@Override
	public NudeRow transform(NudeRow row) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
