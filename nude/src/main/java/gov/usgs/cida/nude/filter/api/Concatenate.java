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
public class Concatenate implements ColumnTransform {

	private static final Logger log = LoggerFactory.getLogger(Concatenate.class);

	private Concatenate() {

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
		
		public Concatenate append(String literal) {
			Concatenate result;
			//TODO
			result = new Concatenate();
			return result;
		}
		public Concatenate append(Column col) {
			Concatenate result;
			//TODO
			result = new Concatenate();
			return result;
		}
	}
}
