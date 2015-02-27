package gov.usgs.cida.nude.filter.api;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.filter.ColumnTransform;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class FormatDate implements ColumnTransform {

	private static final Logger log = LoggerFactory.getLogger(FormatDate.class);

	private FormatDate() {

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
		public ColumnTransformBuilder from(DateTimeFormatter parser) {
			ColumnTransformBuilder result = this;
			//TODO
			return result;
		}
		
		public FormatDate as(DateTimeFormatter formatter) {
			FormatDate result;
			//TODO
			result = new FormatDate();
			return result;
		}
	}
}
