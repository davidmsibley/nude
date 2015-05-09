package gov.usgs.cida.nude.column.impl;

import com.google.common.base.Strings;
import gov.usgs.cida.nude.column.Column;
import java.io.Serializable;
import java.util.Objects;

public class SimpleColumn implements Column, Serializable {
	
	private static final long serialVersionUID = -4261444225742490354L;

	protected final String columnName;
	protected final String tableName;
	protected final String schemaName;
	protected final Class<?> valueType;
	protected final boolean isDisplay;
	
	public SimpleColumn(String columnName) {
		this(columnName, null, null, String.class, true);
	}
	
	public SimpleColumn(String columnName, boolean isDisplayable) {
		this(columnName, null, null, String.class, isDisplayable);
	}
	
	public SimpleColumn(String column, String table, String schema, Class<?> type, boolean isDisplayable) {
		this.columnName = Strings.nullToEmpty(column).trim();
		this.tableName = Strings.nullToEmpty(table).trim();
		this.schemaName = Strings.nullToEmpty(schema).trim();
		this.valueType = type;
		this.isDisplay = isDisplayable;
	}
	
	@Override
	public String getName() {
		return this.columnName;
	}

	@Override
	public String getQualifiedName() {
		StringBuilder result = new StringBuilder();
		String tableName = this.getTableName();
		String colName = this.getName();
		if (!tableName.isEmpty()) {
			result.append(tableName).append(".");
		}
		result.append(colName);
		return result.toString();
	}

	@Override
	public String getFullName() {
		StringBuilder result = new StringBuilder();
		String schemaName = this.getSchemaName();
		String tableName = this.getTableName();
		String colName = this.getName();
		if (!schemaName.isEmpty()) {
			result.append(schemaName).append(".");
		}
		if (!tableName.isEmpty()) {
			result.append(tableName).append(".");
		}
		result.append(colName);
		return result.toString();
	}

	@Override
	public String getTableName() {
		return this.tableName;
	}

	@Override
	public String getSchemaName() {
		return this.schemaName;
	}

	public Class<?> getValueType() {
		return this.valueType;
	}

	@Override
	public boolean isDisplayable() {
		return this.isDisplay;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj instanceof Column) {
			Column rhs = (Column) obj;
			return Objects.equals(this.getFullName(), rhs.getFullName())
				&& Objects.equals(this.getValueType(), rhs.getValueType());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getFullName(), this.getValueType());
	}

	@Override
	public String toString() {
		return this.getFullName();
	}
}
