package gov.usgs.cida.nude.column;

/**
 *
 * @author dmsibley
 */
public interface NudeRow extends Comparable<NudeRow> {
	public String getString(Column column);
	public ColumnGroup getColumns();
	
}
