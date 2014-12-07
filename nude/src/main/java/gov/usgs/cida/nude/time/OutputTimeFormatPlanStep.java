package gov.usgs.cida.nude.time;

import gov.usgs.cida.nude.column.ColumnGrouping;
import gov.usgs.cida.nude.filter.ColumnTransform;
import gov.usgs.cida.nude.filter.FilterStageBuilder;
import gov.usgs.cida.nude.filter.NudeFilter;
import gov.usgs.cida.nude.filter.NudeFilterBuilder;
import gov.usgs.cida.nude.plan.PlanStep;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import java.sql.ResultSet;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class OutputTimeFormatPlanStep implements PlanStep {
	private static final Logger log = LoggerFactory.getLogger(OutputTimeFormatPlanStep.class);

	protected final NudeFilter nudeFilter;

	public OutputTimeFormatPlanStep(final ColumnGrouping inCols, final DateTimeFormatter dtf) {
		
		NudeFilterBuilder nfb = new NudeFilterBuilder();
		this.nudeFilter = nfb.addFilterStage(new FilterStageBuilder().addTransform(inCols.getPrimaryKey(), new ColumnTransform() {
					@Override
					public String transform(TableRow row) {
						String result = null;

						String val = row.getValue(inCols.getPrimaryKey());
						if (null != val) {
							result = dtf.print(Long.parseLong(val));
						}

						return result;
					}
				}).buildFilterStage()).buildFilter();
	}
	
	@Override
	public ResultSet runStep(ResultSet input) {
		return this.nudeFilter.runStep(input);
	}

}
