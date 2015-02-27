package gov.usgs.cida.nude.connector;

import gov.usgs.cida.nude.connector.parser.IParser;

import java.sql.ResultSet;

public interface Connector {
	public void addInput(ResultSet in);
	public String getStatement();
	public ResultSet getResultSet();
	public IParser getParser();
	public boolean isValidInput();
	public boolean isReady();
	public ColumnGrouping getExpectedColumns();
}
