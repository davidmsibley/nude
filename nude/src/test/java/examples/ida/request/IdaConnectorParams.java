package examples.ida.request;

import java.util.Date;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.ColumnTag;
import java.util.Collections;
import java.util.Set;

public enum IdaConnectorParams implements Column {
	SITE_NUMBER,
	GET_DATA(Boolean.class),
	FROM_DATE(Date.class),
	TO_DATE(Date.class);
	
	private Class<?> valueType;
	
	private IdaConnectorParams() {
		this(String.class);
	}
	
	private <T> IdaConnectorParams(Class<T> valueType) {
		this.valueType = valueType;
	}

	@Override
	public String getName() {
		return this.toString();
	}

	@Override
	public String getQualifiedName() {
		return this.getName();
	}

	@Override
	public String getFullName() {
		return this.getName();
	}

	@Override
	public String getTableName() {
		return "";
	}

	@Override
	public String getSchemaName() {
		return "";
	}
	
	@Override
	public Class<?> getValueType() {
		return this.valueType;
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

	@Override
	public Set<ColumnTag> getTags() {
		return Collections.<ColumnTag>emptySet();
	}

}
