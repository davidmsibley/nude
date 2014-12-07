package gov.usgs.cida.nude.filter;

import com.google.common.collect.Lists;
import gov.usgs.cida.nude.column.CGResultSetMetaData;
import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.ColumnGrouping;
import gov.usgs.cida.nude.out.Closers;
import gov.usgs.cida.nude.resultset.inmemory.PeekingResultSet;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilteredResultSet extends PeekingResultSet {
	private static final Logger log = LoggerFactory.getLogger(FilteredResultSet.class);
	
	protected final FilterStage filterStage;
	protected final ResultSet in;
	protected final ColumnGrouping inColumns;
	protected final ColumnGrouping outColumns;
	
	public FilteredResultSet(ResultSet input, FilterStage transform) {
		try {
			this.closed = input.isClosed();
		} catch (AbstractMethodError t) {
			log.trace("we're filtering a 1.4 version of ResultSet");
			this.closed = false;
		} catch (SQLException e) {
			this.closed = true;
		}
		
		this.in = input;
		this.filterStage = transform;
		this.inColumns = ColumnGrouping.getColumnGrouping(input);
		ColumnGrouping transformedCols = new ColumnGrouping(this.inColumns.getPrimaryKey(), Lists.newArrayList(filterStage.transforms.keySet()));
		this.outColumns = ColumnGrouping.join(Lists.newArrayList(this.inColumns, transformedCols));
		this.metadata = new CGResultSetMetaData(outColumns);
	}
	
	protected TableRow buildRow() throws SQLException {
		TableRow result = null;
		
		Map<Column, String> row = new HashMap<Column, String>();
		
		for (Column col : this.inColumns) {
			try {
				row.put(col, this.in.getString(col.getName()));
			} catch (SQLException e) {
				throw e;
			} catch (Exception e) {
				log.debug("Non SQL exception thrown by getString for col " + col.getName() + ": " + e.getMessage());
			}
		}
		
		result = new TableRow(this.inColumns, row);
		
		return result;
	}
	
	@Override
	protected void addNextRow() throws SQLException {
		if (in.next()) {
			this.nextRows.add(buildRow());
		}
	}

	@Override
	public void close() throws SQLException {
		try {
			Closers.closeQuietly(in);
		} finally {
			this.closed = true;
		}
	}
	
	@Override
	public String getString(int columnIndex) throws SQLException {
		throwIfClosed(this);
		throwIfBadLocation(loc);
		return this.filterStage.transform(this.outColumns.get(columnIndex), this.currRow);
	}

	@Override
	public String getCursorName() throws SQLException {
		throwIfClosed(this);
		return in.getCursorName();
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		throwIfClosed(this);
		return this.outColumns.indexOf(columnLabel);
	}

}
