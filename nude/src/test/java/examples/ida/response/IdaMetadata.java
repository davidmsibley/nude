package examples.ida.response;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.SimpleColumn;

public enum IdaMetadata {
	MINDATETIME,
	MAXDATETIME;
	
	private Class<?> valueType;
	
	private IdaMetadata() {
		this.valueType = String.class;
	}
	
	private <T> IdaMetadata(Class<T> valueType) {
		this.valueType = valueType;
	}
	
	public static final String TABLE_NAME = "RESPONSE";
	public static final String SCHEMA_NAME = "IDA_METADATA";
	
	public Column getColumn() {
		return new SimpleColumn(this.toString());
	}
	
}
