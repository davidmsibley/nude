package gov.usgs.cida.nude;

import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.resultset.impl.IteratorWrappingResultSet;
import java.util.Iterator;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class Nude {
	private static final Logger log = LoggerFactory.getLogger(Nude.class);
	
	public static NudeSource source(Iterable<NudeRow> input) {
		return source(input.iterator());
	}
	
	public static NudeSource source(Iterator<NudeRow> input) {
		return NudeResultSetFilter.source(new IteratorWrappingResultSet(input));
	}
	
	public static boolean equals(NudeSink a, NudeSink b) {
		boolean result = true;
		
		if (null != a && null != b) {
			int rowIndex = -1;
			boolean hasRows = true;
			while(result && hasRows) {
				rowIndex++;
				NudeRow aRow = a.pull();
				NudeRow bRow = b.pull();
				
				if ((null != aRow && null == bRow)
						|| (null == aRow && null != bRow)) {
					result = false;
				}
				
				hasRows = (null != aRow && null != bRow);
				if (hasRows) {
					if (!Objects.equals(aRow, bRow)) {
						log.error("Inequal Data at {}.  Expected: {} Actual: {}", new Object[] {rowIndex, aRow, bRow});
						result = false;
					}
				}
			}
		} else if (null == a && null == b) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
}
