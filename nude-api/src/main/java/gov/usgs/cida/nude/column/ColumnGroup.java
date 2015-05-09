package gov.usgs.cida.nude.column;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author dmsibley
 */
public interface ColumnGroup extends Serializable, Collection<Column> {
	public Column getPrimaryKey();
	public ColumnGroup join(ColumnGroup columns);
	public int indexOf(String column);
	public Column get(int index);
}
