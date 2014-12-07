package examples.ida.request;

import gov.usgs.cida.nude.column.Column;
import java.util.Collections;
import java.util.Set;

public enum MetadataRequest implements Column {
	sn;

	public static final String TABLE_NAME = "REQUEST";
	public static final String SCHEMA_NAME = "IDA_METADATA";
	
	private Class<?> valueType;
	
	private MetadataRequest() {
		this.valueType = String.class;
	}
	
	private <T> MetadataRequest(Class<T> valueType) {
		this.valueType = valueType;
	}
	
	@Override
	public String getName() {
		return toString();
	}

	@Override
	public String getQualifiedName() {
		return TABLE_NAME + "." + getName();
	}

	@Override
	public String getFullName() {
		return SCHEMA_NAME + "." + getQualifiedName();
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getSchemaName() {
		return SCHEMA_NAME;
	}

	@Override
	public Class<?> getValueType() {
		return this.valueType;
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}
	
}
