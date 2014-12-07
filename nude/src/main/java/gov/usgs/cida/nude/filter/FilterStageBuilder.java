package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.filter.transform.ColumnAlias;
import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.ColumnGrouping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterStageBuilder {
	protected Map<Column, ColumnTransform> transforms;
	
	public FilterStageBuilder() {
		this.transforms = new HashMap<Column, ColumnTransform>();
		
		//TODO[TAG] make sure things go thru
//		for (Column col : input) {
//			this.addTransform(col, new ColumnAlias(col));
//		}
	}
	
	public FilterStageBuilder addTransform(Column outColumn, ColumnTransform transform) {
		this.transforms.put(outColumn, transform);
		return this;
	}
	
	public FilterStage buildFilterStage() {
		return new FilterStage(this.transforms);
	}
}
