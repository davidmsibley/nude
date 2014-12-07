package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class FilterStage {
	protected final Map<Column, ColumnTransform> transforms;
	
	public FilterStage(Map<Column, ColumnTransform> transform) {
		this.transforms = Collections.unmodifiableMap(transform);
	}
	
	public String transform(Column col, TableRow in) throws SQLException {
		String result = null;
		
		result = this.transforms.get(col).transform(in);
		
		return result;
	}
	
}
