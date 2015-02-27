package gov.usgs.cida.nude.resultset.inmemory;

import com.google.common.base.Objects;
import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.ColumnGrouping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableRow implements Comparable<TableRow>{
	private static final Logger log = LoggerFactory.getLogger(TableRow.class);
	protected final Map<Column, String> row;
	protected final ColumnGrouping columns;
	
	public TableRow(Column primaryKey, String value) {
		this.columns = new ColumnGrouping(primaryKey);
		this.row = new HashMap<Column, String>();
		this.row.put(primaryKey, value);
	}
	
	public TableRow(ColumnGrouping colGroup, Map<Column, String> row) {
		if (null == colGroup) {
			throw new RuntimeException("ColumnGroup cannot be null");
		}
		Map<Column, String> modRow = new HashMap<Column, String>();
		
		if (null != row) {
			if (row.keySet().containsAll(colGroup.getColumns())) {
				modRow.putAll(row);
			} else {
				for (Column col : colGroup.getColumns()) {
					modRow.put(col, row.get(col));
				}
			}
		}
		
		this.row = Collections.unmodifiableMap(modRow);
		this.columns = colGroup;
	}
	
	public String getValue(Column column) {
		return this.row.get(column);
	}
	
	public ColumnGrouping getColumns() {
		return this.columns;
	}
	
	public Set<Entry<Column, String>> getEntries() {
		return this.row.entrySet();
	}
	
	public Map<Column, String> getMap() {
		return this.row;
	}

	/**
	 * Checks to see whether all non-key rows are empty
	 * @return 
	 */
	public boolean isEmpty() {
		boolean result = true;
		for (Entry<Column, String> ent : this.row.entrySet()) {
			if (!this.columns.getPrimaryKey().equals(ent.getKey())) {
				if (!StringUtils.isEmpty(ent.getValue())) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj instanceof TableRow) {
			TableRow rhs = (TableRow) obj;
			return new EqualsBuilder()
					.append(this.getColumns(), rhs.getColumns())
					.append(this.getMap(), rhs.getMap())
					.isEquals();
		}
		return false;
	}
	
	/**
	 * Compares the values of the primary keys. (Values are compared as Longs)
	 */
	@Override
	public int compareTo(TableRow o) {
		int result = -1;
		
		try {
			result = new Long(this.getValue(this.getColumns().getPrimaryKey())).compareTo(new Long(o.getValue(this.getColumns().getPrimaryKey())));
		} catch (NumberFormatException e) {
			log.info("Could not compare primary keys as Longs: " + e.getMessage());
			result = this.getValue(this.getColumns().getPrimaryKey()).compareTo(o.getValue(this.getColumns().getPrimaryKey()));
		}
		
		return result;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(2153, 421)
				.append(columns)
				.append(row)
				.toHashCode();
	}
	
	public static TableRow buildTableRow(ResultSet rs) throws SQLException {
		TableRow result = null;
		
		if (null != rs) {
			ColumnGrouping cg = ColumnGrouping.getColumnGrouping(rs);
			Map<Column, String> row = new HashMap<Column, String>();
			for (Column col : cg) {
				String strVal = rs.getString(col.getName());
				row.put(col, strVal);
			}

			result = new TableRow(cg, row);
		}
		
		return result;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("columns", this.columns)
				.add("row", this.row)
				.toString();
	}
}
