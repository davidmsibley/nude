package gov.usgs.cida.nude.filter.api;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.filter.ColumnTransform;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class Reorder implements ColumnTransform {

	private static final Logger log = LoggerFactory.getLogger(Reorder.class);

	private Reorder() {

	}
	@Override
	public String transform(TableRow row) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public static ColumnTransformBuilder column(Column col) {
		ColumnTransformBuilder result = new ColumnTransformBuilder();
		//TODO
		return result;
	}
	public static class ColumnTransformBuilder {
		
		public Reorder before(Column col) {
			Reorder result;
			//TODO
			result = new Reorder();
			return result;
		}
		public Reorder after(Column col) {
			Reorder result;
			//TODO
			result = new Reorder();
			return result;
		} 
	}
}
