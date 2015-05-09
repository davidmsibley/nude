package gov.usgs.cida.nude;

import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.filter.ColumnTransformer;
import gov.usgs.cida.nude.filter.RowFlattener;

/**
 *
 * @author dmsibley
 */
public interface NudeSource {
	/**
	 * Combine two sources by muxing the new source with the current source
	 * @param source
	 * @return The source to be configured
	 */
	public NudeSource mux(NudeSource source);
	
	/**
	 * Combine two sources by concatenating the new source after the current source
	 * @param source
	 * @return The source to be configured
	 */
	public NudeSource concat(NudeSource source);
	
	
	/**
	 * Flatten from N to M rows (Often by buffering at least N rows)
	 * @param flattener
	 * @return 
	 */
	public NudeSource flatten(RowFlattener flattener);
	
	/**
	 * Transform data within individual columns.
	 * @param transformer
	 * @return 
	 */
	public NudeSource transform(ColumnTransformer transformer);
	
	/**
	 * Add an output from the source
	 * @param sink
	 * @return 
	 */
	public NudeSink sink();
}
