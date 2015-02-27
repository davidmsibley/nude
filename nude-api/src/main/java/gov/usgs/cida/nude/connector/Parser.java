package gov.usgs.cida.nude.connector;

import gov.usgs.cida.nude.column.ColumnGrouping;
import gov.usgs.cida.nude.resultset.inmemory.TypedValue;

import java.io.Reader;
import java.sql.SQLException;

public interface Parser {
	public boolean next(Reader in) throws SQLException;
	public <T> TypedValue<T> getValue(Class<T> type, int index) throws SQLException;
	public ColumnGrouping getAvailableColumns();
}
