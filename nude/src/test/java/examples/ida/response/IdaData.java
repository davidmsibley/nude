package examples.ida.response;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.SimpleColumn;

public enum IdaData {
	site_no,
	date_time,
	tz_cd,
	dd,
	accuracy_cd,
	value,
	precision,
	remark;

	private Class<?> valueType;
	
	private IdaData() {
		this.valueType = String.class;
	}
	
	private <T> IdaData(Class<T> valueType) {
		this.valueType = valueType;
	}
	
	public Column getColumn() {
		return new SimpleColumn(this.toString());
	}
}
