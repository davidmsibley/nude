package gov.usgs.cida.nude.filter.api;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.filter.ColumnTransform;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class Regex implements ColumnTransform {

	private static final Logger log = LoggerFactory.getLogger(Regex.class);

	private Regex() {

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
		
		public ColumnTransformBuilder pattern(Pattern pattern) {
			ColumnTransformBuilder result = this;
			//TODO
			return result;
		}
		public Regex getGroup(int group) {
			Regex result = new Regex();
			//TODO
			return result;
		}
	}
}
