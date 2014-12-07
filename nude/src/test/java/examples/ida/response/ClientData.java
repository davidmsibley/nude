package examples.ida.response;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.SimpleColumn;

public enum ClientData {
	timestamp,
	value;

	private Class<?> valueType;
	
	private ClientData() {
		this.valueType = String.class;
	}
	
	private <T> ClientData(Class<T> valueType) {
		this.valueType = valueType;
	}
	
	public Column getColumn() {
		return new SimpleColumn(this.toString());
	}
	
}
