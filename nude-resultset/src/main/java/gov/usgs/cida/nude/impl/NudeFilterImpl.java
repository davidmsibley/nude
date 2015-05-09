package gov.usgs.cida.nude.impl;

import gov.usgs.cida.nude.NudeSink;
import gov.usgs.cida.nude.NudeSource;
import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.filter.ColumnTransformer;
import gov.usgs.cida.nude.filter.RowFlattener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class NudeFilterImpl implements NudeSource {
	private static final Logger log = LoggerFactory.getLogger(NudeFilterImpl.class);
	private List<NudeSource> sources = new ArrayList<>(); 
	
	public NudeFilterImpl() {
	}
	
	public NudeFilterImpl flatten(RowFlattener flattener) {
		NudeFilterImpl result = this;
		//TODO
		return result;
	}

	public NudeFilterImpl transform(ColumnTransformer transformer) {
		NudeFilterImpl result = this;
		//TODO
		return result;
	}
	
//	
//	public ResultSet filter() {
//		ResultSet result = null;
//		NudeFilter nude = this.build();
//		result = nude.filter();
//		return result;
//	}
//	
//	public NudeFilter build() {
//		NudeFilter result = new NudeFilter();
//		//TODO
//		return result;
//	}

	@Override
	public NudeSource mux(NudeSource source) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public NudeSource concat(NudeSource source) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public NudeSink sink() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
