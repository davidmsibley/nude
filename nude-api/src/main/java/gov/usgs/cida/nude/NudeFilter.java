package gov.usgs.cida.nude;

import gov.usgs.cida.nude.filter.ColumnTransformer;
import gov.usgs.cida.nude.filter.RowFlattener;
import java.sql.ResultSet;

/**
 *
 * @author dmsibley
 */
public interface NudeFilter {
	/**
	 * Source from a Nude Configuration
	 * @param source
	 * @return The source to be configured
	 */
	public NudeFilter source(NudeFilter source);
	
	/**
	 * Source from a ResultSet
	 * @param source
	 * @return The source as a NudeFilter to be further configured
	 */
	public NudeFilter source(ResultSet source);
	
	/**
	 * Flatten from N to M rows (Often by buffering at least N rows)
	 * @param flattener
	 * @return 
	 */
	public NudeFilter flatten(RowFlattener flattener);
	
	/**
	 * Transform data within individual columns.
	 * @param transformer
	 * @return 
	 */
	public NudeFilter transform(ColumnTransformer transformer);
	
	/**
	 * Sink out with current configuration
	 * @return Either a Parent NudeFilter or itself if no Parent
	 */
	public NudeFilter sink();
	
	
}
