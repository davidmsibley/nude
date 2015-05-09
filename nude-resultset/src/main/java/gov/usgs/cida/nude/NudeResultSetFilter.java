package gov.usgs.cida.nude;

import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.filter.ColumnTransformer;
import gov.usgs.cida.nude.filter.RowFlattener;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class NudeResultSetFilter implements NudeSource {
	private static final Logger log = LoggerFactory.getLogger(NudeResultSetFilter.class);
	protected final ResultSet in;
	
	private NudeResultSetFilter(ResultSet input) {
		this.in = input;
	}
	
	public static NudeSource source(ResultSet input) {
		return new NudeResultSetFilter(input);
	}

	@Override
	public NudeSource mux(NudeSource source) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public NudeSource concat(NudeSource source) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	@Override
	public NudeSource flatten(RowFlattener flattener) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public NudeSource transform(ColumnTransformer transformer) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public NudeSink sink() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
