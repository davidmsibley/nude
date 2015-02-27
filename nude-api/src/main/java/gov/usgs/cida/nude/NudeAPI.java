package gov.usgs.cida.nude;

import gov.usgs.cida.nude.connector.Connector;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmsibley
 */
public class NudeAPI {
	private List<NudeAPISource> sources = new ArrayList<NudeAPISource>(); 
	
	public NudeAPI() {
	}
	
	public NudeAPISource source() {
		NudeAPISource result = new NudeAPISource(this, null);
		this.sources.add(result);
		return result;
	}
	
	public NudeAPISource source(ResultSet source) {
		NudeAPISource result = new NudeAPISource(this, source);
		this.sources.add(result);
		return result;
	}
	
	public NudeAPI sink() {
		NudeAPI result = this;
		return result;
	}
	
	public ResultSet filter() {
		ResultSet result = null;
		Nude nude = this.build();
		result = nude.filter();
		return result;
	}
	
	public Nude build() {
		Nude result = new Nude();
		//TODO
		return result;
	}
	
	public static class NudeAPISource {
		private final NudeAPI parent;
		private final ResultSet in;
		
		protected NudeAPISource(NudeAPI parent, ResultSet in) {
			this.parent = parent;
			this.in = in;
		}
		
		public NudeAPISource flatten(RowFlattener flattener) {
			NudeAPISource result = this;
			//TODO
			return result;
		}
		
		public NudeAPISource transform(ColumnTransform transformer) {
			NudeAPISource result = this;
			//TODO
			return result;
		}
		
		public NudeAPI sink() {
			NudeAPI result = parent;
			return result;
		}
		
		public NudeAPISource source() {
			NudeAPISource result = this.sink().source();
			return result;
		}
		
		public NudeAPISource source(ResultSet source) {
			NudeAPISource result = this.sink().source(source);
			return result;
		}
		
		public ResultSet filter() {
			ResultSet result = null;
			NudeAPI nfb = this.sink();
			result = nfb.filter();
			return result;
		}
	}
}
