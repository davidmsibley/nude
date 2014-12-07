package gov.usgs.cida.nude.filter;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dmsibley
 */
public class NudeFilterBuilder {
	protected final List<FilterStage> filterStages;
	
	public NudeFilterBuilder() {
		this.filterStages = new LinkedList<FilterStage>();
	}
	
	public NudeFilterBuilder addFilterStage(FilterStage stage) {
		this.filterStages.add(stage);
		return this;
	}
	
	public NudeFilter buildFilter() {
		return new NudeFilter(this.filterStages);
	}
	
}
