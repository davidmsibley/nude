package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.filter.NudeAPI.NudeAPISource;
import java.sql.ResultSet;

public class Nude {
	
	public Nude() {
	}

	public FilteredResultSet filter() {
		FilteredResultSet result = null;
		
//		for (FilterStage filterStage : this.filterStages) {
//			if (null == result) {
//				result = new FilteredResultSet(input, filterStage);
//			} else {
//				result = new FilteredResultSet(result, filterStage);
//			}
//		}
		
		return result;
	}
	
	public static NudeAPISource source() {
		return new NudeAPI().source();
	}
	public static NudeAPISource source(ResultSet source) {
		return new NudeAPI().source(source);
	}
}
