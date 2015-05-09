package gov.usgs.cida.nude.column.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Ordering;
import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.ColumnGroup;
import gov.usgs.cida.nude.column.NudeRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NudeRowImpl implements NudeRow {
	
	private static final long serialVersionUID = -4005364945642342712L;
	
	private static final Logger log = LoggerFactory.getLogger(NudeRowImpl.class);

	protected final Map<Column, String> row;
	protected final ColumnGroup columns;
	
	public NudeRowImpl(Column primaryKey, String value) {
		this.columns = new ColumnGroupImpl(primaryKey);
		this.row = new HashMap<Column, String>();
		this.row.put(primaryKey, value);
	}
	
	public NudeRowImpl(ColumnGroup colGroup, Map<Column, String> row) {
		if (null == colGroup) {
			throw new RuntimeException("ColumnGroup cannot be null");
		}
		Map<Column, String> modRow = new HashMap<Column, String>();
		
		if (null != row) {
			if (row.keySet().containsAll(colGroup)) {
				modRow.putAll(row);
			} else {
				for (Column col : colGroup) {
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
	
	@Override
	public String getString(Column column) {
		return this.getValue(column);
	}
	
	@Override
	public Comparable getPrimaryValue() {
		return new Long(this.getString(this.getColumns().getPrimaryKey()));
	}
	
	@Override
	public ColumnGroup getColumns() {
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
				if (!Strings.isNullOrEmpty(ent.getValue())) {
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
		if (obj instanceof NudeRowImpl) {
			NudeRowImpl rhs = (NudeRowImpl) obj;
			return Objects.equals(this.getColumns(), rhs.getColumns())
					&& Objects.equals(this.getMap(), rhs.getMap());
		}
		return false;
	}
	
	/**
	 * Compares the values of the primary keys. (Values are compared as Longs)
	 */
	@Override
	public int compareTo(NudeRow o) {
		int result = -1;
		
		try {
			result = Ordering.natural().compare(this.getPrimaryValue(), o.getPrimaryValue());
		} catch (NumberFormatException e) {
			log.info("Could not compare primary keys as Longs: " + e.getMessage());
			result = this.getString(this.getColumns().getPrimaryKey()).compareTo(o.getString(this.getColumns().getPrimaryKey()));
		}
		
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.columns, this.row);
	}
	
	public static NudeRowImpl buildNudeRow(ResultSet rs) throws SQLException {
		NudeRowImpl result = null;
		
		if (null != rs) {
			ColumnGroup cg = ColumnGroupImpl.getColumnGrouping(rs);
			Map<Column, String> row = new HashMap<Column, String>();
			for (Column col : cg) {
				String strVal = rs.getString(col.getName());
				row.put(col, strVal);
			}

			result = new NudeRowImpl(cg, row);
		}
		
		return result;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("columns", this.columns)
				.add("row", this.row)
				.toString();
	}

}
