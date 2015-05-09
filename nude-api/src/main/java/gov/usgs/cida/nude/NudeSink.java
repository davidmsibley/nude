package gov.usgs.cida.nude;

import gov.usgs.cida.nude.column.NudeRow;

/**
 *
 * @author dmsibley
 */
public interface NudeSink {
	public NudeRow pull();
}
