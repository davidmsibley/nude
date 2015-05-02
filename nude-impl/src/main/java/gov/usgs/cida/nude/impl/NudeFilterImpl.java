package gov.usgs.cida.nude.impl;

import gov.usgs.cida.nude.NudeFilter;
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
public class NudeFilterImpl implements NudeFilter {
	private static final Logger log = LoggerFactory.getLogger(NudeFilterImpl.class);
	private List<NudeFilter> sources = new ArrayList<>(); 
	
	public NudeFilterImpl() {
	}
	
	public NudeFilterImpl source(NudeFilter source) {
		NudeFilterImpl result = new NudeFilterImpl();
		this.sources.add(result);
		return result;
	}
	
	public NudeFilterImpl source(ResultSet source) {
		NudeFilterImpl result = new NudeFilterImpl();
		this.sources.add(result);
		return result;
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
	
	public NudeFilterImpl sink() {
		NudeFilterImpl result = this;
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
}
