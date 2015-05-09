package gov.usgs.cida.nude.column;

import java.io.Serializable;

/**
 *
 * @author dmsibley
 */
public interface NudeRow extends Comparable<NudeRow>, Serializable {
	public Comparable getPrimaryValue();
	public String getString(Column column);
	public ColumnGroup getColumns();
	
}
