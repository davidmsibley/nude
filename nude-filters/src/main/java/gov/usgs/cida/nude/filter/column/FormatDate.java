/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.usgs.cida.nude.filter.column;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.filter.ColumnTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class FormatDate implements ColumnTransformer {
	private static final Logger log = LoggerFactory.getLogger(FormatDate.class);
	
	private final Column column;
	
	private FormatDate(Column column) {
		this.column = column;
	}
	
	public static FormatDate column(Column column) {
		return new FormatDate(column);
	}

	@Override
	public NudeRow transform(NudeRow row) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
