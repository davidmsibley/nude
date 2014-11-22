package gov.usgs.cida.nude.column;

import java.util.Set;

public interface Column {

	/**
	 * @return the name of the column
	 */
	public String getName();

	/**
	 * @return &lt;tableName&gt;.&lt;columnName&gt;
	 */
	public String getQualifiedName();

	/**
	 * @return &lt;schemaName&gt;.&lt;tableName&gt;.&lt;columnName&gt;
	 */
	public String getFullName();

	/**
	 * @return the name of the table the column belongs to
	 */
	public String getTableName();

	/**
	 * @return the name of the schema the columns belongs to
	 */
	public String getSchemaName();
	
	/**
	 * @return the Class that this column is expected to hold
	 */
	public Class<?> getValueType();
	
	/**
	 * @return If this column should be displayed in the end result
	 */
	public boolean isDisplayable();
	
	/**
	 * @return a set of tags that indicate which operations can be run on this column.
	 */
	public Set<ColumnTag> getTags();
}
